package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OneToOne(optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Long id;
    private WarehouseEntity warehouseEntity;
    private List<WarehouseEntity> basketList;
}
