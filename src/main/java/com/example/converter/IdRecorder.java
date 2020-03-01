package com.example.converter;

import com.example.entity.OrderEntity;
import com.example.entity.ProductItemEntity;
import org.springframework.stereotype.Component;

@Component
public class IdRecorder {
    public ProductItemEntity nullRecorder(ProductItemEntity productItemEntity){
        productItemEntity.setBasketEntity(null);
        return productItemEntity;
    }
}
