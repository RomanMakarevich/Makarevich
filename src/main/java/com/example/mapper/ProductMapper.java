package com.example.mapper;

import com.example.dto.ProductDTO;
import com.example.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity sourceToDestination(ProductDTO source);

    ProductDTO destinationToSource(ProductEntity destination);
}
