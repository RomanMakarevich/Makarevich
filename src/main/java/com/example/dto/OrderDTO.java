package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
    private long id;
    private String fio;
    private String companyName;
    private String address;
    private String accountNumber;
    private String productName;
    private long numberOfProduct;
    private long totalCost;
}
