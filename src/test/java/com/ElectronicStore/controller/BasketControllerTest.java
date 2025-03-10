package com.ElectronicStore.controller;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.ElectronicStore.constant.TestConstant.CUSTOMER_TOKEN;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setupSecurityContext() {
            UserDetails userDetails = new User(
                    "customer",
                    "password123",
                    List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
            );

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                        List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    @Order(1)
    public void testAddProductToBasket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/basket")
                        .header("Authorization", "Bearer " + CUSTOMER_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\": 1, \"quantity\": 2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        mockMvc.perform(MockMvcRequestBuilders.post("/basket")
                        .header("Authorization", "Bearer " + CUSTOMER_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\": 2, \"quantity\": 3}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @Order(2)
    public void testCalculateReceipt() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/basket/receipt")
                        .header("Authorization", "Bearer " + CUSTOMER_TOKEN)
                        .param("basketId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Receipt:\n" +
                        "Product 1 x 2 = 100.0\n" +
                        "Product 2 x 3 = 225.0\n" +
                        "Total: 325.0"));
    }

    @Test
    @Order(3)
    public void testRemoveProductFromBasket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/basket")
                        .header("Authorization", "Bearer " + CUSTOMER_TOKEN)
                        .param("basketId", "1")
                        .param("productId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.items").isArray());
    }
}