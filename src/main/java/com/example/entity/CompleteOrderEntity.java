package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity(name = "completeOrder")
public class CompleteOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fio;
    private String companyNameCustomer;
    private String addressCustomer;
    private String accountNumberCustomer;
    private String companyNameSeller;
    private String addressSeller;
    private String accountNumberSeller;
    @OneToOne
    @JoinColumn(name = "basket_id", nullable = false)
    private BasketEntity basketEntity;
    private double totalCost;
}
