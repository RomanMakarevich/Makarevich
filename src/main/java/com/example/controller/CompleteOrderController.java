package com.example.controller;

import com.example.dto.CompleteOrderDTO;
import com.example.service.CompleteOrderService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

@Log
@Data
@RestController
@RequestMapping("/product-factory-app")
public class CompleteOrderController {
    private final CompleteOrderService completeOrderService;

    @PostMapping (value = "/orders/{orderId}")
    public CompleteOrderDTO completeOrder(@PathVariable final long orderId) throws Exception{
        return completeOrderService.completeOrder(orderId);
    }
}
