package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductItemDTO {
    private ProductDTO productDTO;
    private long numberOfProduct;
}
