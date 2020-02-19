package com.example.mapper;

import com.example.dto.CompleteOrderDTO;
import com.example.entity.CompleteOrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "string")
public interface CompleteOrderMapper {
    CompleteOrderEntity sourceToDestination(CompleteOrderDTO source);


    CompleteOrderDTO destinationToSource(CompleteOrderEntity destination);
}
