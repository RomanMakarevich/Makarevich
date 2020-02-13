package com.example.service;

import com.example.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeOrderService {
    public List<OrderDTO> makeOrder(final long userId){
        return List.of(OrderDTO.builder()
                .id(1)
                .fio("Пупкин Василий Иванович")
                .companyName("Пивной бар №1")
                .adress("г. Минск, ул. Пивная, 1")
                .accountNumber("1111 2222 3333 4444")
                .productName("keg")
                .numberOfKeg(100)
                .totalCost(1000)
                .build());
    }
}
