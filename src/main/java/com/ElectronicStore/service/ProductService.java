package com.ElectronicStore.service;

import com.ElectronicStore.dto.DiscountDealDTO;
import com.ElectronicStore.dto.ProductDTO;
import com.ElectronicStore.entity.DiscountDeal;
import com.ElectronicStore.entity.Product;
import com.ElectronicStore.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private ModelMapper modelMapper;

    private DiscountDealService discountDealService;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    public void removeProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO applyDiscountDealToProduct(Long productId, Long discountId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        DiscountDealDTO discountDeal = discountDealService.getDiscountDeal(discountId);
        product.setDiscountDeal(modelMapper.map(discountDeal, DiscountDeal.class));
        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }
}