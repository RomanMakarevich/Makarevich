package com.example.service;

import com.example.dto.WarehouseDTO;
import com.example.entity.BasketEntity;
import com.example.entity.WarehouseEntity;
import com.example.mapper.WarehouseMapper;
import com.example.reposiroty.BasketRepositoty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddBasketListService {

    final BasketRepositoty basketRepositoty;
    WarehouseEntity warehouseEntity;
    BasketEntity basketEntity;
    final WarehouseMapper warehouseMapper;

    public void addBasketList(final long userId, final WarehouseDTO request) {
        warehouseEntity = warehouseMapper.sourceToDestination(request);
        basketEntity.setBasketList(List.of(warehouseEntity));
        basketRepositoty.save(basketEntity);
    }
}
