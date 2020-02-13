package com.example.service;

import com.example.dto.CompleteOrderDTO;
import com.example.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompliteOrderService {
    public List<CompleteOrderDTO> getList(final long orderId){
        return List.of(CompleteOrderDTO.builder()
                .orderId(1)
                .fio("Пупкин Василий Иванович")
                .companyNameCustomer("Пивной бар №1")
                .adressCustomer("г. Минск, ул. Пивная, 1")
                .accountNumberCustomer("1111 2222 3333 4444")
                .companyNameSeller("Завод тары для пива")
                .adressSeller("г. Минск, ул. Предприятий связанных с пивом")
                .accountNumberSeller("2222 6666 4444 8888")
                .productName("keg")
                .numberOfKeg(100)
                .totalCost(1000)
                .build());

    }
}
