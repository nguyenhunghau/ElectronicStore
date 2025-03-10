package com.ElectronicStore.service;


import com.ElectronicStore.dto.DiscountDealDTO;
import com.ElectronicStore.entity.DiscountDeal;
import com.ElectronicStore.repositories.DiscountDealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DiscountDealServiceTest {

    @InjectMocks
    private DiscountDealService discountDealService;

    @Mock
    private DiscountDealRepository discountDealRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDiscountDealById() {
        DiscountDeal discountDeal = new DiscountDeal();
        discountDeal.setId(1L);
        DiscountDealDTO discountDealDTO = new DiscountDealDTO();
        discountDealDTO.setId(1L);

        when(discountDealRepository.findById(1L)).thenReturn(Optional.of(discountDeal));
        when(discountDealRepository.save(any())).thenReturn(discountDeal);

        when(modelMapper.map(discountDeal, DiscountDealDTO.class)).thenReturn(discountDealDTO);
        DiscountDealDTO result = discountDealService.getDiscountDeal(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testGetDiscountDealById_NotFound() {
        when(discountDealRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> discountDealService.getDiscountDeal(1L));
    }
}