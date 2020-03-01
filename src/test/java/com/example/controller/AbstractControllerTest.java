package com.example.controller;

import com.example.dto.ProductDTO;
import com.example.dto.UserSignInResponseDTO;
import com.example.entity.*;
import com.example.mapper.OrderMapper;
import com.example.mapper.ProductMapper;
import com.example.reposiroty.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static com.example.security.Roles.USER;
import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @MockBean
    protected AuthInfoRepository authInfoRepository;
    @MockBean
    protected UserRepository userRepository;
    @MockBean
    protected BasketRepository basketRepository;
    @MockBean
    protected ProductRepository productRepository;
    @MockBean
    protected OrderRepository orderRepository;
    @MockBean
    protected WarehouseRepository warehouseRepository;
    @MockBean
    protected CompleteOrderRepository completeOrderRepository;

    protected String signInAsUser() throws Exception {
        final AuthInfoEntity authInfo = createAuthInfo();
        willReturn(Optional.of(authInfo)).given(authInfoRepository).findByLogin("vasya@email.com");

        final String response = mockMvc.perform(post("/user/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"email\" : \"vasya@email.com\",\n" +
                        "  \"password\" : \"qwerty\"\n" +
                        "}"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("token", hasLength(144)))
                .andReturn().getResponse().getContentAsString();

        verify(authInfoRepository, times(1));
        return "Bearer " + objectMapper.readValue(response, UserSignInResponseDTO.class).getToken();
    }

    protected AuthInfoEntity createAuthInfo() {
        final UserEntity user = new UserEntity();
        user.setUserRole(USER);
        user.setEmail("vasya@email.com");

        final AuthInfoEntity authInfo = new AuthInfoEntity();
        authInfo.setLogin(user.getEmail());
        authInfo.setPassword(passwordEncoder.encode("qwerty"));
        authInfo.setUserEntity(user);
        return authInfo;
    }

    protected ProductEntity createProduct() {
        final ProductEntity productEntity = productMapper.sourceToDestination(ProductDTO.builder()
                .productId(1)
                .productName("keg")
                .material("steel")
                .weight(7.1)
                .cost(100.0)
                .build());
        productEntity.setId((long) 1);
        return productEntity;
    }

    protected UserEntity createUser() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId((long) 1);
        userEntity.setEmail("vasya@email.com");
        userEntity.setFio("Пупкин Василий Иванович");
        userEntity.setCompanyName("Пивной бар №1");
        userEntity.setAddress("г. Минск, ул. Пивная, 1");
        userEntity.setAccountNumber("1111 2222 3333 4444");
        userEntity.setUserRole(USER);
        return userEntity;
    }

    protected BasketEntity createBasket() {
        final BasketEntity basketEntity = new BasketEntity();
        basketEntity.setId((long) 1);
        basketEntity.setBasketList(List.of(createProductItem()));
        basketEntity.setUserEntity(createUser());
        basketEntity.setTotalCost(basketEntity.
                getTotalCost() + basketEntity.
                getBasketList().
                stream().
                mapToDouble(ProductItemEntity::getCost).
                sum());
        return basketEntity;
    }

    protected ProductItemEntity createProductItem() {
        final ProductItemEntity productItemEntity = new ProductItemEntity();
        productItemEntity.setNumberOfProduct((long) 100);
        productItemEntity.setProductEntity(createProduct());
        productItemEntity.setCost(productItemEntity.getProductEntity().getCost() * productItemEntity.getNumberOfProduct());

        return productItemEntity;
    }

    protected OrderEntity createOrder() {
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId((long) 1);
        orderEntity.setUserEntity(createUser());
        orderEntity.setBasketList(List.of(createProductItem()));
        orderEntity.setTotalCost(10000.00);

        return orderEntity;
    }

    protected WarehouseEntity createWarehouse() {
        final WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setNumberOfProduct((long) 1000);
        warehouseEntity.setId((long) 1);
        warehouseEntity.setProductEntity(createProduct());

        return warehouseEntity;
    }

    protected CompleteOrderEntity createCompleteOrder() {
        final CompleteOrderEntity completeOrderEntity = new CompleteOrderEntity();
        completeOrderEntity.setId((long) 1);
        completeOrderEntity.setFio("Пупкин Василий Иванович");
        completeOrderEntity.setCompanyNameCustomer("Пивной бар №1");
        completeOrderEntity.setAddressCustomer("г. Минск, ул. Пивная, 1");
        completeOrderEntity.setAccountNumberCustomer("1111 2222 3333 4444");
        completeOrderEntity.setCompanyNameSeller("Завод тары для пива");
        completeOrderEntity.setAddressSeller("г. Минск, ул. Предприятий связанных с пивом");
        completeOrderEntity.setAddressSeller("2222 6666 4444 8888");
//        completeOrderEntity.setBasketList(createBasket().getBasketList());
        completeOrderEntity.setTotalCost((long) 10000);

        return completeOrderEntity;
    }
}
