package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOrder() throws Exception {
        mockMvc.perform(get("/product-factory-app/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        " \"id\" : 1, \n" +
                        " \"fio\" : \"Пупкин Василий Иванович\", \n" +
                        " \"companyName\" : \"Пивной бар №1\", \n" +
                        " \"adress\" : \"г. Минск, ул. Пивная, 1\", \n" +
                        " \"accountNumber\" : \"1111 2222 3333 4444\", \n" +
                        " \"productName\" : \"keg\", \n" +
                        " \"numberOfKeg\": 100, \n" +
                        " \"totalCost\" : 1000 \n" +
                        "  }\n" +
                        "]"));

    }
}
