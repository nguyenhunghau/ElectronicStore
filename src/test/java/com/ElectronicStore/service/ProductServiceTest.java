package com.ElectronicStore.service;


import com.ElectronicStore.dto.ProductDTO;
import com.ElectronicStore.entity.Product;
import com.ElectronicStore.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setDescription("Test Description");
        productDTO.setPrice(100.0);

        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);

        when(modelMapper.map(productDTO, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);

        ProductDTO createdProduct = productService.createProduct(productDTO);
        assertThat(createdProduct).isNotNull();
        assertThat(createdProduct.getName()).isEqualTo("Test Product");

        verify(productRepository, times(1)).save(product);
    }
}