package com.example.mapper;

import com.example.dto.CompleteOrderDTO;
import com.example.entity.CompliteOrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "string")
public interface CompliteOrderMapper {
    CompliteOrderEntity sourceToDestination(CompleteOrderDTO source);


    CompleteOrderDTO destinationToSource(CompliteOrderEntity destination);
}
