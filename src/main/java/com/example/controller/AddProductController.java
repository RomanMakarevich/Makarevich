package com.example.controller;

import com.example.dto.ProductDTO;
import com.example.service.AddProductService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@Data
@RestController
@RequestMapping("/product-factory-app")
public class AddProductController {

    private final AddProductService addProductService;

    @PutMapping (value = "/products/{productId}")
    private final List<ProductDTO> addProduct(@PathVariable final Long productId){
    return addProductService.getList(productId);
    }
}
