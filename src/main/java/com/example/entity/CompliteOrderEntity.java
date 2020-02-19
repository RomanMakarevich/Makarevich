package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "compliteOrder")
public class CompliteOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fio;
    private String companyNameCustomer;
    private String adressCustomer;
    private String accountNumberCustomer;
    private String companyNameSeller;
    private String adressSeller;
    private String accountNumberSeller;
//    private List<WarehouseEntity> basketList;
    private double totalCost;
}
