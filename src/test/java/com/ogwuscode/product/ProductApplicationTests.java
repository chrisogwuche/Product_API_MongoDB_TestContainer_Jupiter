package com.ogwuscode.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogwuscode.product.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Slf4j
class ProductApplicationTests {

	@Container
	final static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

	@DynamicPropertySource
	static void setProperty(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongo.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void shouldCreateProduct() throws Exception {
		String productToString = objectMapper.writeValueAsString(getProduct());

		MockHttpServletResponse mockHttpServletResponse =
				mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/create")
								.contentType(MediaType.APPLICATION_JSON)
								.content(productToString))
						.andExpect(status().isCreated())
						.andReturn().getResponse();

		log.info("Status: "+mockHttpServletResponse.getStatus());
		log.info("Content: "+mockHttpServletResponse.getContentAsString());


		Assertions.assertEquals(201,mockHttpServletResponse.getStatus());

	}

	private Product getProduct(){

		return Product.builder()
				.productName("Apple")
				.price(BigDecimal.valueOf(1000))
				.build();
	}
}
