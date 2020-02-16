package com.example.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

    private long productId;
    private String productName;
    private String material;
    private double weight;

}
