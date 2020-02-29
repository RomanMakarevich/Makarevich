package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;

import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource("classpath:application-test.properties")
public class WarehouseControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void addProduct() throws Exception {
//        willReturn(Optional.of(createWarehouse())).given(warehouseRepository).getOne((long) 1);
        mockMvc.perform(put("/product-factory-app/products/1")
                .header("productId", 1)
                .param("numberOfProduct", "20"))

                .andExpect(status().isOk())
        ;
    }
}
