package com.example.mapper;

import com.example.dto.WarehouseDTO;
import com.example.entity.WarehouseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseEntity sourceToDestination(WarehouseDTO source);

    WarehouseDTO destinationToSource(WarehouseEntity destination);
}
