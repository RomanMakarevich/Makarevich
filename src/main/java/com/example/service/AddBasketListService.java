package com.example.service;

import antlr.collections.impl.LList;
import com.example.entity.BasketEntity;
import com.example.entity.ProductItemEntity;
import com.example.mapper.ProductItemMapper;
import com.example.reposiroty.BasketRepository;
import com.example.reposiroty.ProductRepository;
import com.example.reposiroty.UserRepository;
import liquibase.pro.packaged.E;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.LinkedList;
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

        final LinkedList<ProductItemEntity> basketList = new LinkedList();
        basketList.add(createProductItem(productId, numberOfProduct));

        if (basketRepository.findByUserId(userId) != null) {

            basketList.addAll(basketRepository.findByUserId(userId).getBasketList());
            basketList.getFirst().setBasketEntity(basketRepository.findByUserId(userId));
            basketRepository.findByUserId(userId).setBasketList(basketList);

        } else {
            BasketEntity basketEntity = new BasketEntity();
            basketList.getFirst().setBasketEntity(basketRepository.findByUserId(userId));
            basketEntity.setBasketList(basketList);
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

    private ProductItemEntity createProductItem(final long productId, final Long numberOfProduct) {
        final ProductItemEntity productItemEntity = new ProductItemEntity();
        productItemEntity.setProductEntity(productRepository.getOne(productId));
        productItemEntity.setNumberOfProduct(numberOfProduct);
        productItemEntity.setCost(productItemEntity.getProductEntity().getCost() * productItemEntity.getNumberOfProduct());

        return productItemEntity;
    }
}
