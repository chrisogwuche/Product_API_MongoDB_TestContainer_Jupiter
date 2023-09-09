package com.ogwuscode.product.service;

import com.ogwuscode.product.dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    String createProduct(ProductDto productDto);
}
