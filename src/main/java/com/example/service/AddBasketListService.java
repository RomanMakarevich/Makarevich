package com.example.service;

import com.example.dto.ProductItemDTO;
import com.example.dto.WarehouseDTO;
import com.example.entity.BasketEntity;
import com.example.entity.ProductItemEntity;
import com.example.entity.WarehouseEntity;
import com.example.mapper.ProductItemMapper;
import com.example.mapper.WarehouseMapper;
import com.example.reposiroty.BasketRepositoty;
import com.example.reposiroty.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AddBasketListService {

    final BasketRepositoty basketRepositoty;

    final ProductItemMapper productItemMapper;
    final UserRepository userRepository;

    @Transactional
    public void addBasketList(final long userId, final ProductItemDTO request) {
        final  ProductItemEntity productItemEntity = productItemMapper.sourceToDestination(request);
        BasketEntity basketEntity = new BasketEntity();
        basketEntity.setBasketList(List.of(productItemEntity));
        basketEntity.setUserEntity(userRepository.getOne(userId));
        basketEntity.setTotalCost(basketEntity.
                getTotalCost() + basketEntity.
                getBasketList().
                stream().
                mapToDouble(ProductItemEntity::getCost).
                sum());

        basketRepositoty.save(basketEntity);
    }
}
