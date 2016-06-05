package com.ruipgferreira.bliptechchallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruipgferreira.bliptechchallenge.models.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	
}
