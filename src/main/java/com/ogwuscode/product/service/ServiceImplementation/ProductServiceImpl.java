package com.ogwuscode.product.service.ServiceImplementation;

import com.ogwuscode.product.dto.ProductDto;
import com.ogwuscode.product.model.Product;
import com.ogwuscode.product.repository.ProductRepository;
import com.ogwuscode.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public String createProduct(ProductDto productDto) {
        var product = Product.builder()
                .productName(productDto.getProductName())
                .price(productDto.getPrice())
                .build();

        productRepository.save(product);

        return "product created";
    }
}
