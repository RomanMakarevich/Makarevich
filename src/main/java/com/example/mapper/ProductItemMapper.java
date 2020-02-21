package com.example.mapper;

import com.example.dto.ProductItemDTO;
import com.example.entity.ProductItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ProductItemMapper {
    ProductItemEntity sourceToDestination(ProductItemDTO source);

    ProductItemDTO destinationToSource(ProductItemEntity destination);
}
