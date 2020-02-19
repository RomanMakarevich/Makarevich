package com.example.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.List;


@Data
@Builder
public class CompleteOrderDTO {


    private long orderId;
    private String fio;
    private String companyNameCustomer;
    private String addressCustomer;
    private String accountNumberCustomer;
    private String companyNameSeller;
    private String addressSeller;
    private String accountNumberSeller;
//    private List<WarehouseDTO> basketList;
    private long totalCost;

}
