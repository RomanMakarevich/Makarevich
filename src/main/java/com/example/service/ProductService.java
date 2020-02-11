package com.example.service;


import com.example.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public List<ProductDTO> getList() {
        return List.of(ProductDTO.builder()
                .productName("keg")
                .material("sreel")
                .weight(7.1)
                .numberOfKeg(1000)
                .build());
    }
}


