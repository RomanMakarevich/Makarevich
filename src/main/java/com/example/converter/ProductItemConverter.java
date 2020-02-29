package com.example.converter;

import com.example.dto.ProductItemDTO;
import com.example.entity.ProductItemEntity;
import com.example.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductItemConverter {
    final ProductMapper productMapper;
    public ProductItemDTO entityToDTO(ProductItemEntity productItemEntity){
        final ProductItemDTO productItemDTO = ProductItemDTO.builder()
                .productDTO(productMapper.destinationToSource(productItemEntity.getProductEntity()))
                .numberOfProduct(productItemEntity.getNumberOfProduct())
                .build();
        return productItemDTO;
    }
}
