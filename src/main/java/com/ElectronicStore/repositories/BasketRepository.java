package com.ElectronicStore.repositories;

import com.ElectronicStore.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findByAccountId(Long accountId);
}