package com.example.controller;

import com.example.dto.CompleteOrderDTO;
import com.example.service.CompliteOrderService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@Data
@RestController
@RequestMapping("/product-factory-app")
public class CompliteOrderController {
    private final CompliteOrderService compliteOrderService;

    @PostMapping (value = "/orders/{orderId}")
    public CompleteOrderDTO compliteOrder(@PathVariable final long orderId) throws Exception{
        return compliteOrderService.compliteOrder(orderId);
    }
}
