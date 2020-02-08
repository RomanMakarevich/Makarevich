package com.example.service;

import com.example.dto.UserSignInRequestDTO;
import com.example.dto.UserSignUpRequestDTO;
import org.springframework.stereotype.Service;

/**
 * @author Wladimir Litvinov
 */
@Service
public class UserService {
    public String signUp(final UserSignUpRequestDTO request) {
        return "{\"id\":1}";
    }

    public String signIn(final UserSignInRequestDTO request) {
        return "{\"id\":1}";
    }
}
