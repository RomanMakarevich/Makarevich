package com.example.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AddBasketListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void TestAddBasketList() throws Exception {
//        mockMvc.perform(post("/product-factory-app/basket/${productId}/add-basket-list")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\n" +
//                        " \"productName\" : \"keg\",\n" +
//                        " \"material\" : sreel, \n" +
//                        " \"weight\" : 7.1, \n" +
//                        " \"numberOfKeg\": 100 \n" +
//                        "}"))
//
//                .andExpect(status().isOk());
    }
}
