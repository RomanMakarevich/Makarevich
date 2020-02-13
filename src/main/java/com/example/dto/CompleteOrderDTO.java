package com.example.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.java.Log;


@Data
@Builder
public class CompleteOrderDTO {


    private long orderId;
    private String fio;
    private String companyNameCustomer;
    private String adressCustomer;
    private String accountNumberCustomer;
    private String companyNameSeller;
    private String adressSeller;
    private String accountNumberSeller;
    private ProductDTO productDTO;
    private String productName;
    private long numberOfKeg;
    private long totalCost;

//    private OrderDTO orderDTO;
//    private long orderId = orderDTO.getId();
//    private UserSignUpRequestDTO userSignUpRequestDTO;
//    private String fio = userSignUpRequestDTO.getFio();
//    private String companyNameCustomer = userSignUpRequestDTO.getCompanyName();
//    private String adressCustomer = userSignUpRequestDTO.getAdress();
//    private String accountNumberCustomer = userSignUpRequestDTO.getAccountNumber();
//    private String companyNameSeller;
//    private String adressSeller;
//    private String accountNumberSeller;
//    private ProductDTO productDTO;
//    private String productName = productDTO.getProductName();
//    private long numberOfKeg = productDTO.getNumberOfKeg();
//    private long totalCost;

}
