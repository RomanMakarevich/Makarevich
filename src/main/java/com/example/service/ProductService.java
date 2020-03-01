package com.example.service;


import com.example.dto.ProductDTO;
import com.example.entity.ProductEntity;
import com.example.mapper.ProductMapper;
import com.example.reposiroty.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public void init() {
        productRepository.save(productMapper.sourceToDestination(ProductDTO.builder()
                .productName("keg")
                .material("steel")
                .weight(7.1)
                .cost(100.0)
                .build()));

    }

    public List<ProductDTO> getList() {
        return productRepository.findAll().stream().map(productMapper::destinationToSource).collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProduct(final long id) {
        return productRepository.findById(id).map(productMapper::destinationToSource);
    }

    public void createProduct(final ProductDTO product) {
        final ProductEntity productEntity = productMapper.sourceToDestination(product);
        productRepository.save(productEntity);
    }
}


