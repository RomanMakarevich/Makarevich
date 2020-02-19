package com.example.mapper;

import com.example.dto.BasketDTO;
import com.example.entity.BasketEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "string")
public interface BasketMapper {
    BasketEntity sourceToDestination(BasketDTO source);

    BasketDTO destinationToSource(BasketEntity destination);
}
