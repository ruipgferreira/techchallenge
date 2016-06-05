package com.ruipgferreira.bliptechchallenge.services;

import java.util.List;

import com.ruipgferreira.bliptechchallenge.models.Purchase;

public interface IPurchaseService {

	public List<Purchase> getAllPurchases();
	
	public List<Purchase> getAllValidPurchases();
	
	public Purchase getPurchase(Long purchaseId);

	public Purchase getValidPurchase(Long purchaseId);

	public void savePurchase(Purchase purchase);
	
}
