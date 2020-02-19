package com.example.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BasketDTO {
    private List<WarehouseDTO> basketList;
    private double totalCost;
}
