package com.example.entity;

import com.example.security.Roles;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String fio;
    private String companyName;
    private String adress;
    private String accountNumber;

    private Roles userRole;
}
