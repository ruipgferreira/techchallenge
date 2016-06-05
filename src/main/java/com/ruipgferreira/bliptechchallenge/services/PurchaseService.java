package com.ruipgferreira.bliptechchallenge.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruipgferreira.bliptechchallenge.models.Purchase;
import com.ruipgferreira.bliptechchallenge.repositories.PurchaseRepository;

@Service
public class PurchaseService implements IPurchaseService {
	
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	public PurchaseService(PurchaseRepository purchaseRepository) {
		this.purchaseRepository = purchaseRepository;
	}
	
	public List<Purchase> getAllPurchases() {
		return purchaseRepository.findAll();
	}	
	
	public List<Purchase> getAllValidPurchases() {
		Date dateNow = new Date();
		return purchaseRepository.findAll().stream().filter(u -> (u.getExpiresUTC() == null) || (u.getExpiresUTC().compareTo(dateNow) > 0) ).collect(Collectors.toList());
	}
	
	public Purchase getPurchase(Long purchaseId) {
		return purchaseRepository.findOne(purchaseId);
	}	
	

	public Purchase getValidPurchase(Long purchaseId) {
		Date dateNow = new Date();
		return purchaseRepository.findAll().stream()
				.filter(u -> (u.getId() == purchaseId) && ( (u.getExpiresUTC() == null) || (u.getExpiresUTC().compareTo(dateNow) > 0) ))
				.findFirst()
				.orElse(null);
	}		

	public void savePurchase(Purchase purchase) {
		purchase.setPurchaseDetails(purchase.getPurchaseDetails());
		purchaseRepository.save(purchase);
		purchaseRepository.flush();
	}
	
}
