package com.example.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class OrderDTO {
    private long id;
    private String fio;
    private String companyName;
    private String address;
    private String accountNumber;
    private List<ProductItemDTO> basketList;
    private double totalCost;
}
