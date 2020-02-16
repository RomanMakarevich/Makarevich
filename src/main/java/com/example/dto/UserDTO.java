package com.example.dto;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String email;
    private String fio;
    private String companyName;
    private String adress;
    private String accountNumber;
}
