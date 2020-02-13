package com.example.service;

import com.example.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddProductService {
    public List<ProductDTO> getList(final long productId) {
        return List.of(ProductDTO.builder()
                .productId(1)
                .productName("keg")
                .material("sreel")
                .weight(7.1)
                .numberOfKeg(1000)
                .build());
    }
}