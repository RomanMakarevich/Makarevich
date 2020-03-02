package com.example.controller;


import com.example.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
@TestPropertySource("classpath:application-test.properties")
public class ProductControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetListOfProduct() throws Exception {
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

    @Test
    public void testGetProduct() throws Exception {
        willReturn(createProduct()).given(productRepository).getOne((long) 1);
        final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YUBlbWFpbC5jb20iLCJleHAiOjE1ODE4MTY1OTQsImlhdCI6MTU4MTc4MDU5NH0.zLBFfajJ1RuyIaTuYpsa-YdjdZP1DIIpxLWbOZS6YGo";
        mockMvc.perform(get("/product-factory-app/products/1").header("Authorization", token)
                .header("productId", 1))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        " \"productId\" : 1, \n" +
                        " \"productName\" : \"keg\", \n" +
                        " \"material\" : steel, \n" +
                        " \"weight\" : 7.1, \n" +
                        " \"cost\" : 100.0 \n" +
                        "  }\n" +
                        "]"));

    }

    @Test
    public void testCreateProduct() throws Exception {
        final ProductEntity product = createSaveProduct();
        willReturn(product).given(productRepository).save(product);

        final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YUBlbWFpbC5jb20iLCJleHAiOjE1ODE4MTY1OTQsImlhdCI6MTU4MTc4MDU5NH0.zLBFfajJ1RuyIaTuYpsa-YdjdZP1DIIpxLWbOZS6YGo";

        mockMvc.perform(post("/product-factory-app/product/orders").header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"productName\" : \"bank\"," +
                        "\"material\" : \"steel\"," +
                        "\"weight\" : 0.3," +
                        "\"cost\" : 5.0" +
                        "}"))

                .andExpect(status().isCreated());

        verify(productRepository, Mockito.times(1)).save(product);
    }
}
