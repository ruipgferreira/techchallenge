package com.ruipgferreira.bliptechchallenge.services;

import java.util.List;

import com.ruipgferreira.bliptechchallenge.models.Purchase;

public interface IPurchaseService {
	
	/**
	 * Returns the list of all purchases including it details
	 * 
	 * @return List of all purchases
	 */
	public List<Purchase> getAllPurchases();
	
	/**
	 * Returns the list of all purchases, including details, filtered by expiration date.
	 * Only the purchases that have a null or future expiration date are returned.
	 * 
	 * @return List of all valid purchases
	 */
	public List<Purchase> getAllValidPurchases();
	
	/**
	 * Returns a specific purchase from the list of all purchases
	 * 
	 * @param purchaseId the id of the desired purchase
	 * @return Purchase with a given id
	 */
	public Purchase getPurchase(Long purchaseId);

	/**
	 * Returns a specific purchase from the list of purchases filtered by expiration date
	 * 
	 * @param purchaseId the id of the desired purchase
	 * @return Purchase with a given id
	 */
	public Purchase getValidPurchase(Long purchaseId);

	public void savePurchase(Purchase purchase);
	
}
