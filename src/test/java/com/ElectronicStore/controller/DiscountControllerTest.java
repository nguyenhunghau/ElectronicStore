//package com.ElectronicStore.controller;
//
//import com.ElectronicStore.dto.DiscountType;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static com.ElectronicStore.constant.TestConstant.ADMIN_TOKEN;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class DiscountControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testCreateDiscountDeal() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/discounts")
//                        .header("Authorization", "Bearer " + ADMIN_TOKEN)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"discountType\": \"PRICE\", \"description\": \"10% off on orders above $100\", \"discountPercentage\": 10.0, \"requiredQuantity\": null}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(3L))
//                .andExpect(jsonPath("$.discountType").value(DiscountType.PRICE.name()))
//                .andExpect(jsonPath("$.description").value("10% off on orders above $100"))
//                .andExpect(jsonPath("$.discountPercentage").value(10.0));
//    }
//}