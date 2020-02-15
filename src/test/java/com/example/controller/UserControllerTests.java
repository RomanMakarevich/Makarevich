package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasLength;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUserSignUpIsCreated() throws Exception {
        // given
        // when
        mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"vasya@email.com\",\n" +
                        "  \"password\" : \"qwerty\",\n" +
                        "  \"fio\" : \"Пупкин Василий Иванович\",\n" +
                        "  \"company name\" : \"Пивной бар №1\",\n" +
                        "  \"adress\" : \"г. Минск, ул. Пивная, 1\",\n" +
                        "  \"account number\" : \"1111 2222 3333 4444\" \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("token", hasLength(144)));
    }

    @Test
    public void testUserSignInIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(post("/user/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"vasya@email.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(144)));
    }

    @Test
    public void testAddBasketList() throws Exception {
        mockMvc.perform(put("/user/1/basket/1")
                .header("userId", 1)
                .header("productId", 1))

                .andExpect(status().isOk());
    }

    @Test
    public void testCreateOrder() throws Exception {
        mockMvc.perform(post("/user/1/basket")
                .header("userId", 1))

                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        " \"id\" : 1, \n" +
                        " \"fio\" : \"Пупкин Василий Иванович\",\n" +
                        " \"companyName\" : \"Пивной бар №1\",\n" +
                        " \"adress\" : \"г. Минск, ул. Пивная, 1\", \n" +
                        " \"accountNumber\" : \"1111 2222 3333 4444\", \n" +
                        " \"productName\" : \"keg\", \n" +
                        " \"numberOfKeg\": 100, \n" +
                        " \"totalCost\" : 1000 \n" +
                        "  }\n" +
                        "]"));

    }

}