package com.example.controller;


import com.example.dto.ProductDTO;
import com.example.service.ProductService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Log
@Data
@RestController
@RequestMapping("/product-factory-app/basket")
public class AddBasketListController {

//    private final ProductService productService;
//
//    @PostMapping(value = "/{productId}/add-basket-list", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void addBascetList(@PathVariable final long productId){
//
//    }
}
