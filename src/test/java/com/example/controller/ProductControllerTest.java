package com.example.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
@TestPropertySource("classpath:application-test.properties")
public class ProductControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testProduct() throws Exception {
        willReturn(List.of(createProduct())).given(productRepository).findAll();
        final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YUBlbWFpbC5jb20iLCJleHAiOjE1ODE4MTY1OTQsImlhdCI6MTU4MTc4MDU5NH0.zLBFfajJ1RuyIaTuYpsa-YdjdZP1DIIpxLWbOZS6YGo";
        mockMvc.perform(get("/product-factory-app/products").header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        " \"productId\" : 0, \n" +
                        " \"productName\" : \"keg\", \n" +
                        " \"material\" : steel, \n" +
                        " \"weight\" : 7.1, \n" +
                        " \"cost\" : 100.0 \n" +
                        "  }\n" +
                        "]"));

    }
}
