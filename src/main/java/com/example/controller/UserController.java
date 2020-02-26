package com.example.controller;

import com.example.dto.*;
import com.example.ecxeption.SuchUserAlreadyExistException;
import com.example.security.JwtUtil;
import com.example.service.AddBasketListService;
import com.example.service.OrderService;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log
@Data
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AddBasketListService addBasketListService;
    private final OrderService orderService;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponseDTO singUp(@RequestBody final UserSignUpRequestDTO request) throws SuchUserAlreadyExistException {
        userService.signUp(request);

        return singIn(new UserSignInRequestDTO(request.getEmail(), request.getPassword()));
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserSignInResponseDTO singIn(@RequestBody final UserSignInRequestDTO request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        return new UserSignInResponseDTO
                (jwtUtil.generateToken(new User(request.getLogin(), request.getPassword(), List.of(new SimpleGrantedAuthority("USER")))));
    }

    @PutMapping(value = "/{userId}/basket/{productId}")
    public void addBasketList(@PathVariable final Long userId,
                              @PathVariable final Long productId,
                              @RequestParam final Long numberOfProduct) {
        addBasketListService.addBasketList(userId, productId, numberOfProduct);

    }

    @PostMapping(value = "/{userId}/basket")
    public OrderDTO createOrder(@PathVariable final Long userId) {
        return orderService.createOrder(userId);
    }

}
