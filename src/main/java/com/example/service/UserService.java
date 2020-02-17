package com.example.service;

import com.example.dto.UserSignUpRequestDTO;
import com.example.ecxeption.SuchUserAlreadyExistException;
import com.example.entity.AuthInfoEntity;
import com.example.entity.UserEntity;
import com.example.mapper.UserSignUpRequestMapper;
import com.example.reposiroty.AuthInfoRepository;
import com.example.reposiroty.UserRepository;
import com.example.security.Roles;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor

public class UserService {

    private final AuthInfoRepository authInfoRepository;
    private final UserRepository userRepository;
    private final UserSignUpRequestMapper userSignUpRequestMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(final UserSignUpRequestDTO request) throws SuchUserAlreadyExistException {
        if (authInfoRepository.findByLogin(request.getEmail()).isPresent()) {
            throw new SuchUserAlreadyExistException("User with email=" + request.getEmail() + " already exists");
        }
        saveUser(request);
    }

    private void saveUser(final UserSignUpRequestDTO request) {
        final UserEntity userEntity = userSignUpRequestMapper.sourceToDestination(request);
        userEntity.setUserRole(Roles.USER);
        final UserEntity savedUser = userRepository.save(userEntity);
        saveAuthInfo(request, savedUser);
    }

    private void saveAuthInfo(final UserSignUpRequestDTO request, final UserEntity savedUser) {
        final AuthInfoEntity authInfoEntity = new AuthInfoEntity();
        authInfoEntity.setEmail(request.getEmail());
        authInfoEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        authInfoEntity.setUserEntity(savedUser);
        authInfoRepository.save(authInfoEntity);
    }

}
