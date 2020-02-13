package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CompliteOrderTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void compliteOrder() throws Exception {
        mockMvc.perform(post("/product-factory-app/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(" [\n" +
                        "  {\n" +
                        " \"orderId\" : 1, \n" +
                        " \"fio\" : \"Пупкин Василий Иванович\", \n" +
                        " \"companyNameCustomer\" : \"Пивной бар №1\", \n" +
                        " \"adressCustomer\" : \"г. Минск, ул. Пивная, 1\", \n" +
                        " \"accountNumberCustomer\" : \"1111 2222 3333 4444\", \n" +
                        " \"companyNameSeller\" :\"Завод тары для пива\", \n" +
                        " \"adressSeller\" :\"г. Минск, ул. Предприятий связанных с пивом\", \n" +
                        " \"accountNumberSeller\" :\"2222 6666 4444 8888\", \n" +
                        " \"productName\" : \"keg\", \n" +
                        " \"numberOfKeg\": 100,\n" +
                        " \"totalCost\" : 1000  \n" +
                        "  }\n" +
                        " ]"));
    }
}
