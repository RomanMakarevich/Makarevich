package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasLength;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource("classpath:application-test.properties")
public class UnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void unitTest() throws Exception {
        // given
        // when
        mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"vasya@email.com\",\n" +
                        "  \"password\" : \"qwerty\",\n" +
                        "  \"fio\" : \"Пупкин Василий Иванович\",\n" +
                        "  \"companyName\" : \"Пивной бар №1\",\n" +
                        "  \"address\" : \"г. Минск, ул. Пивная, 1\",\n" +
                        "  \"accountNumber\" : \"1111 2222 3333 4444\" \n" +
                        "}"))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("token", hasLength(144)));

        mockMvc.perform(post("/user/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"login\" : \"vasya@email.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(144)));

        mockMvc.perform(get("/product-factory-app/products"))
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

        mockMvc.perform(put("/user/1/basket/1")
                .header("userId", 1)
                .param("productId", "1")
                .param("numberOfProduct", "100"))

                .andExpect(status().isOk());

        mockMvc.perform(post("/user/1/basket")
                .header("userId", 1))

                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        " \"id\" : 1, \n" +
                        " \"fio\" : \"Пупкин Василий Иванович\",\n" +
                        " \"companyName\" : \"Пивной бар №1\",\n" +
                        " \"address\" : \"г. Минск, ул. Пивная, 1\", \n" +
                        " \"accountNumber\" : \"1111 2222 3333 4444\", \n" +
                        " \"basketList\" : \n" +
                        "[\n" +
                        "{\n" +
                        " \"productDTO\": \n" +
                        "{\n" +
                        " \"productId\":0, \n" +
                        " \"productName\":\"keg\", \n" +
                        " \"material\":\"steel\", \n" +
                        " \"weight\":7.1, \n" +
                        " \"cost\":100.0 \n" +
                        "},\n" +
                        " \"numberOfProduct\":100 \n" +
                        "}\n" +
                        "],\n" +
                        " \"totalCost\" : 10000.00 \n" +
                        " }\n"));
    }

}
