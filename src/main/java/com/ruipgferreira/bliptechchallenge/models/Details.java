package com.ruipgferreira.bliptechchallenge.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DETAILS")
public class Details {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "QUANTITY")
	private Integer quantity;
	
	@Column(name = "VALUE")
	private Double value;
	
    @OneToOne
    @JoinColumn(name = "PURCHASE_ID")
    @MapsId
    private Purchase purchase;
	
	public Details() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}	
	
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
}
