package com.rebakure.bestshoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebakure.bestshoes.dtos.ProductDto;
import com.rebakure.bestshoes.dtos.ProductRequest;
import com.rebakure.bestshoes.services.ProductsService;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
class ProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductsService productsService;

    @Test
    void addProduct_validPriceAndRequiredFields_returns201() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setCategoryId(1);
        request.setVariantId(2L);
        request.setName("Running Shoes");
        request.setBasePrice(BigDecimal.valueOf(120));

        ProductDto response = new ProductDto();
        response.setId(1L);

        when(productsService.addProduct(any())).thenReturn(response);

        mockMvc.perform(post("/products")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void addProduct_negativePrice_returns400() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setCategoryId(1);
        request.setVariantId(2L);
        request.setName("Running Shoes");
        request.setBasePrice(BigDecimal.valueOf(-10));

        mockMvc.perform(post("/products")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addProduct_missingRequiredFields_returns400() throws Exception {
        ProductRequest request = new ProductRequest();

        mockMvc.perform(post("/products")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}