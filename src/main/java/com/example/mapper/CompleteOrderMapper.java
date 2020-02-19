package com.example.mapper;

import com.example.dto.CompleteOrderDTO;
import com.example.entity.CompleteOrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompleteOrderMapper {
    CompleteOrderEntity sourceToDestination(CompleteOrderDTO source);


    CompleteOrderDTO destinationToSource(CompleteOrderEntity destination);
}
