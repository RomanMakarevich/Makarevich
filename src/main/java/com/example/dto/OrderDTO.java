package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
    private long id;
    private String fio;
    private String companyName;
    private String adress;
    private String accountNumber;
    private String productName;
    private long numberOfKeg;
    private long totalCost;
}
