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

    final ProductItemConverter productItemConverter;

    public OrderDTO entityToDTO(OrderEntity orderEntity) {

        final OrderDTO orderDTO = OrderDTO.builder()
                .id(orderEntity.getId())
                .fio(orderEntity.getUserEntity().getFio())
                .companyName(orderEntity.getUserEntity().getCompanyName())
                .address(orderEntity.getUserEntity().getAddress())
                .accountNumber(orderEntity.getUserEntity().getAccountNumber())
                .basketList(orderEntity
                        .getBasketList()
                        .stream()
                        .map(productItemConverter::entityToDTO)
                        .collect(Collectors.toList()))
                .totalCost(orderEntity.getTotalCost())
                .build();

        return orderDTO;
    }
}
