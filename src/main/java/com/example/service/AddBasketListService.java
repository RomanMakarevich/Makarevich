package com.example.service;

import com.example.entity.BasketEntity;
import com.example.entity.ProductItemEntity;
import com.example.mapper.ProductItemMapper;
import com.example.reposiroty.BasketRepository;
import com.example.reposiroty.ProductRepository;
import com.example.reposiroty.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AddBasketListService {

    final BasketRepository basketRepository;
    final ProductRepository productRepository;
    final ProductItemMapper productItemMapper;
    final UserRepository userRepository;

    @Transactional
    public void addBasketList(final long userId, final long productId, final Long numberOfProduct) {

        final ProductItemEntity productItemEntity = new ProductItemEntity();
        productItemEntity.setProductEntity(productRepository.getOne(productId));
        productItemEntity.setNumberOfProduct(numberOfProduct);
        productItemEntity.setCost(productItemEntity.getProductEntity().getCost() * productItemEntity.getNumberOfProduct());

        BasketEntity basketEntity = new BasketEntity();
        basketEntity.setBasketList(List.of(productItemEntity));
        basketEntity.setUserEntity(userRepository.getOne(userId));
        basketEntity.setTotalCost(basketEntity.
                getTotalCost() + basketEntity.
                getBasketList().
                stream().
                mapToDouble(ProductItemEntity::getCost).
                sum());

        basketRepository.save(basketEntity);
    }
}
