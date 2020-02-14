package com.example.controller;

import com.example.dto.OrderDTO;
import com.example.dto.UserSignInRequestDTO;
import com.example.dto.UserSignUpRequestDTO;
import com.example.service.AddBasketListService;
import com.example.service.MakeOrderService;
import com.example.service.UserService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log
@Data
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AddBasketListService addBasketListService;
    private final MakeOrderService makeOrderService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String singUp(@RequestBody final UserSignUpRequestDTO request) {
        return userService.signUp(request);
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String singIn(@RequestBody final UserSignInRequestDTO request) {
        return userService.signIn(request);
    }

    @PutMapping(value = "/{userId}/basket/{productId}")
    public void addBasketList(@PathVariable final Long userId,
                              @PathVariable final Long productId) {
        addBasketListService.addBasketList(userId, productId);

    }

    @PostMapping(value = "/{userId}/basket")
    public List<OrderDTO> makeOrder(@PathVariable final Long userId) {
        return makeOrderService.makeOrder(userId);
    }

}
