package com.example.dto;


import lombok.Data;


@Data
public class UserSignUpRequestDTO {
    private String email;
    private String password;
    private String fio;
    private String companyName;
    private String address;
    private String accountNumber;
}
