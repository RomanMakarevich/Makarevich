package com.example.service;

import com.example.dto.ProductDTO;
import com.example.dto.WarehouseDTO;
import com.example.entity.WarehouseEntity;
import com.example.mapper.WarehouseMapper;
import com.example.reposiroty.WarehouseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@NoArgsConstructor
public class WarehouseService {

    WarehouseRepository warehouseRepository;
    WarehouseMapper warehouseMapper;

    @Transactional
    public void addProduct(final long productId, final long numberOfProduct) {
        warehouseRepository.getOne(productId)
                .setNumberOfProduct(warehouseRepository.
                        getOne(productId).getNumberOfProduct() + numberOfProduct);
    }

    public final WarehouseDTO getProduct(final long productId) {
        return warehouseMapper.destinationToSource(warehouseRepository.getOne(productId));
    }
}
