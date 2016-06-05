package com.ruipgferreira.bliptechchallenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ruipgferreira.bliptechchallenge.models.Purchase;
import com.ruipgferreira.bliptechchallenge.services.PurchaseService;

@RestController
public class PurchaseController {
	
	private PurchaseService purchaseSvc;
	
	@Autowired
	PurchaseController(PurchaseService purchaseSvc) {
		this.purchaseSvc = purchaseSvc;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> get() {

        return new ResponseEntity<>("Purchase Controller", HttpStatus.OK);
	}
	
    //-------------------Retrieve All Purchases--------------------------------------------------------
	
	@RequestMapping(value = "/purchases", method = RequestMethod.GET)
	public ResponseEntity<List<Purchase>> getAllPurchases() {
		List<Purchase> purchases = purchaseSvc.getAllPurchases();
		
        if(purchases.isEmpty()){
            return new ResponseEntity<List<Purchase>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Purchase>>(purchases, HttpStatus.OK);
	}

    //-------------------Retrieve All Valid Purchases--------------------------------------------------------
	
	@RequestMapping(value = "/valid/purchases", method = RequestMethod.GET)
	public ResponseEntity<List<Purchase>> getAllValidPurchases() {
		List<Purchase> purchases = purchaseSvc.getAllValidPurchases();
		
        if(purchases.isEmpty()){
            return new ResponseEntity<List<Purchase>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Purchase>>(purchases, HttpStatus.OK);
	}
	
	//-------------------Retrieve Single Purchase--------------------------------------------------------
	
	@RequestMapping(value = "/purchases/{id}", method = RequestMethod.GET)
	public ResponseEntity<Purchase> getPurchase(@PathVariable Long id) {
		Purchase purchase = purchaseSvc.getPurchase(id);
        
        if (purchase == null) {
            return new ResponseEntity<Purchase>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Purchase>(purchase, HttpStatus.OK);
	}	
	
	//-------------------Retrieve Single Valid Purchase--------------------------------------------------------
	
	@RequestMapping(value = "/valid/purchases/{id}", method = RequestMethod.GET)
	public ResponseEntity<Purchase> getValidPurchase(@PathVariable Long id) {
		Purchase purchase = purchaseSvc.getValidPurchase(id);
        
        if (purchase == null) {
            return new ResponseEntity<Purchase>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Purchase>(purchase, HttpStatus.OK);
	}
	
	//-------------------Create a Purchase--------------------------------------------------------
	
    @RequestMapping(value = "/purchases/", method = RequestMethod.POST)
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase, UriComponentsBuilder ucBuilder) {
    	purchaseSvc.savePurchase(purchase);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/purchase/{id}").buildAndExpand(purchase.getId()).toUri());
        return new ResponseEntity<Purchase>(headers, HttpStatus.CREATED);
    }

	//-------------------Update a Purchase--------------------------------------------------------    
    
    @RequestMapping(value = "/purchases/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Purchase> updatePurchase(@PathVariable("id") long id, @RequestBody Purchase purchase) {
        
        Purchase currentPurchase = purchaseSvc.getValidPurchase(id);
         
        if (currentPurchase==null) {
            return new ResponseEntity<Purchase>(HttpStatus.NOT_FOUND);
        }
 
        currentPurchase.setProductType(purchase.getProductType());
        currentPurchase.setExpiresUTC(purchase.getExpiresUTC());
         
        purchaseSvc.savePurchase(currentPurchase);
        return new ResponseEntity<Purchase>(currentPurchase, HttpStatus.OK);
    }    
    
}
