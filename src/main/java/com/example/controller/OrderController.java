package com.example.controller;

import com.example.dto.OrderDTO;
import com.example.service.OrderService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@Data
@RestController
@RequestMapping("/product-factory-app")
public class OrderController {
    private final OrderService orderService;


    @GetMapping(value = "/orders/{orderId}")
    public OrderDTO getList(final long orderId) {
        return orderService.getList(orderId);
    }
}
