package com.example.service;


import com.example.dto.ProductDTO;
import com.example.mapper.ProductMapper;
import com.example.reposiroty.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void init() {
        productRepository.save(productMapper.sourceToDestination(ProductDTO.builder()
                .productId(1)
                .productName("keg")
                .material("sreel")
                .weight(7.1)
                .build()));

    }

    public List<ProductDTO> getList(){
        return productRepository.findAll().stream().map(productMapper::destinationToSource).collect(Collectors.toList());
    }
    public Optional<ProductDTO> getProduct(final long id){
        return productRepository.findById(id).map(productMapper::destinationToSource);
    }
}


