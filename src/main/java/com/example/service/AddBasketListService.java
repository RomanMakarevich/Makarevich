package com.example.service;

import antlr.collections.impl.LList;
import com.example.entity.BasketEntity;
import com.example.entity.ProductItemEntity;
import com.example.mapper.ProductItemMapper;
import com.example.reposiroty.BasketRepository;
import com.example.reposiroty.ProductItemRepository;
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
    final UserRepository userRepository;
    final ProductItemRepository productItemRepository;

    @Transactional
    public void addBasketList(final long userId, final long productId, final Long numberOfProduct) {


        if (basketRepository.findByUserId(userId) != null) {
            final LinkedList<ProductItemEntity> basketList = new LinkedList();
            basketList.add(createProductItem(productId, numberOfProduct, basketRepository.findByUserId(userId)));
            basketList.addAll(basketRepository.findByUserId(userId).getBasketList());

            basketRepository.findByUserId(userId).setBasketList(basketList);

        } else {
            BasketEntity basketEntity = new BasketEntity();
            basketEntity.setBasketList(List.of(createProductItem(productId, numberOfProduct, basketEntity)));
            basketEntity.setUserEntity(userRepository.getOne(userId));
            basketEntity.setTotalCost(basketEntity.
                    getTotalCost() + basketEntity.
                    getBasketList().
                    stream().
                    mapToDouble(ProductItemEntity::getCost).
                    sum());

            basketRepository.save(basketEntity);
            productItemRepository.save(basketEntity.getBasketList().get(0));
        }
    }

    private ProductItemEntity createProductItem(final long productId, final Long numberOfProduct, final BasketEntity basketEntity) {
        final ProductItemEntity productItemEntity = new ProductItemEntity();
        productItemEntity.setProductEntity(productRepository.getOne(productId));
        productItemEntity.setNumberOfProduct(numberOfProduct);
        productItemEntity.setCost(productItemEntity.getProductEntity().getCost() * productItemEntity.getNumberOfProduct());
        productItemEntity.setBasketEntity(basketEntity);

        return productItemEntity;
    }
}
