package com.ogwuscode.product.controller;

import com.ogwuscode.product.dto.ProductDto;
import com.ogwuscode.product.service.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public String createProduct(@RequestBody @NonNull ProductDto productDto){

        return productService.createProduct(productDto);
    }
}
