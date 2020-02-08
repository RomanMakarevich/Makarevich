package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Wladimir Litvinov
 */
@Data
public class UserSignUpRequestDTO {
    private String email;
    private String password;
    private String fio;
    private String companyName;
    private String adress;
    private String accountNumber;
}
