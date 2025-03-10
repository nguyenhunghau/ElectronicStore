package com.ElectronicStore.controller;

import com.ElectronicStore.dto.ProductDTO;
import com.ElectronicStore.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/products")
@AllArgsConstructor
public class AdminProductController {

    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long productId) {
        productService.removeProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{productId}/discount/{discountId}")
    public ResponseEntity<ProductDTO> applyDiscountDealToProduct(
            @PathVariable Long productId,
            @PathVariable Long discountId) {
        ProductDTO updatedProduct = productService.applyDiscountDealToProduct(productId, discountId);
        return ResponseEntity.ok(updatedProduct);
    }
}
