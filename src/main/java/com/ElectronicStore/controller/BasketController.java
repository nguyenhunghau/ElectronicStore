package com.ElectronicStore.controller;


import com.ElectronicStore.dto.BasketDTO;
import com.ElectronicStore.dto.BasketItemDTO;
import com.ElectronicStore.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
@AllArgsConstructor
public class BasketController {

    private BasketService basketService;

    @PostMapping
    public ResponseEntity<BasketDTO> addProductToBasket(@RequestBody BasketItemDTO basketItemDTO) {
        BasketDTO updatedBasket = basketService.addProductToBasket(basketItemDTO);
        return ResponseEntity.ok(updatedBasket);
    }

    @DeleteMapping
    public ResponseEntity<BasketDTO> removeProductFromBasket(@RequestParam Long basketId, @RequestParam Long productId) {
        BasketDTO updatedBasket = basketService.removeProductFromBasket(basketId, productId);
        return ResponseEntity.ok(updatedBasket);
    }

    @GetMapping("/receipt")
    public ResponseEntity<String> calculateReceipt(@RequestParam Long basketId) {
        String receipt = basketService.calculateReceipt(basketId);
        return ResponseEntity.ok(receipt);
    }
}