package com.example.mapper;

import com.example.dto.OrderDTO;
import com.example.entity.OrderEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity sourceToDestination(OrderDTO source);

    OrderDTO destinationToSource(OrderEntity destination);
}
