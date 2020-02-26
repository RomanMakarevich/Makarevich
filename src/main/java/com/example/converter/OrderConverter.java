package com.example.converter;

import com.example.dto.OrderDTO;
import com.example.entity.OrderEntity;
import com.example.mapper.ProductItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrderConverter {

    final ProductItemMapper productItemMapper;

    public OrderDTO entityToDTO(OrderEntity orderEntity) {

        final OrderDTO orderDTO = OrderDTO.builder()
                .id(orderEntity.getId())
                .fio(orderEntity.getUserEntity().getFio())
                .companyName(orderEntity.getUserEntity().getCompanyName())
                .address(orderEntity.getUserEntity().getAddress())
                .accountNumber(orderEntity.getUserEntity().getAccountNumber())
                .basketList(orderEntity
                        .getBasketEntity()
                        .getBasketList()
                        .stream()
                        .map(productItemMapper::destinationToSource)
                        .collect(Collectors.toList()))
                .totalCost(orderEntity.getTotalCost())
                .build();

        return orderDTO;
    }
}
