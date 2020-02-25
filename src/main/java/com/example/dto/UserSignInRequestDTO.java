package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Wladimir Litvinov
 */
@AllArgsConstructor
@Data
public class UserSignInRequestDTO {

    private String login;
    private String password;
}
