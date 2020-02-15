package com.example.service;

import com.example.dto.UserSignInRequestDTO;
import com.example.dto.UserSignUpRequestDTO;
import com.example.ecxeption.SuchUserAlreadyExistException;
import com.example.security.LoadUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Wladimir Litvinov
 */
@Service
@AllArgsConstructor
public class UserService {

    private final LoadUserDetailService loadUserDetailService;

    public void signUp(final UserSignUpRequestDTO request) throws SuchUserAlreadyExistException {
        if (loadUserDetailService.loadUserByUsername(request.getEmail()) != null) {
            throw new SuchUserAlreadyExistException();
        }
        loadUserDetailService.saveUser(request.getEmail(), request.getPassword());
    }

    public String signIn(final UserSignInRequestDTO request) throws SuchUserAlreadyExistException{
        return "{\"id\":1}";
    }
}
