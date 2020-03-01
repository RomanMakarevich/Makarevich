package com.example.service;

import com.example.dto.WarehouseDTO;
import com.example.mapper.WarehouseMapper;
import com.example.reposiroty.WarehouseRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@NoArgsConstructor
public class WarehouseService {

    WarehouseRepository warehouseRepository;
    WarehouseMapper warehouseMapper;

    @Transactional
    public void addProduct(final long productId, final long numberOfProduct) {
        warehouseRepository.findByProductId(productId)
                .setNumberOfProduct(warehouseRepository.
                        findByProductId(productId).getNumberOfProduct() + numberOfProduct);
    }

    public final WarehouseDTO getProduct(final long productId) {
        return warehouseMapper.destinationToSource(warehouseRepository.findByProductId(productId));
    }
}
