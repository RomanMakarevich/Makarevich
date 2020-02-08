package com.example.dto;

import lombok.Data;

/**
 * @author Wladimir Litvinov
 */
@Data
public class UserSignInRequestDTO {
    private String email;
    private String password;
}
