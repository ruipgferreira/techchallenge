package com.ruipgferreira.bliptechchallenge.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="PURCHASE")
public class Purchase {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@Column(name = "PRODUCT_TYPE", length = 50)
	private String productType;
	
	@Column(name = "EXPIRES_UTC")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date expiresUTC;
	
	@OneToOne(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
	private Details purchaseDetails;
	
	public Purchase() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Date getExpiresUTC() {
		return expiresUTC;
	}
	public void setExpiresUTC(Date expiresUTC) {
		this.expiresUTC = expiresUTC;
	}
	public Details getPurchaseDetails() {
		return purchaseDetails;
	}
	public void setPurchaseDetails(Details purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
		this.purchaseDetails.setPurchase(this);
	}
}
