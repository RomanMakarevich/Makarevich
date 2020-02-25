package com.example.service;

import com.example.dto.ProductItemDTO;
import com.example.dto.WarehouseDTO;
import com.example.entity.BasketEntity;
import com.example.entity.ProductEntity;
import com.example.entity.ProductItemEntity;
import com.example.entity.WarehouseEntity;
import com.example.mapper.ProductItemMapper;
import com.example.mapper.WarehouseMapper;
import com.example.reposiroty.BasketRepositoty;
import com.example.reposiroty.ProductRepository;
import com.example.reposiroty.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.jsse.PEMFile;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class AddBasketListService {

    final BasketRepositoty basketRepositoty;
    final ProductRepository productRepository;
    final ProductItemMapper productItemMapper;
    final UserRepository userRepository;

    @Transactional
    public void addBasketList(final long userId, final long productID, final long numberOfProduct) {

        final ProductItemEntity productItemEntity = new ProductItemEntity();
        productItemEntity.setProductEntity(productRepository.getOne(productID));
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

        basketRepositoty.save(basketEntity);
    }
}
