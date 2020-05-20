package com.webshop.webshop.Order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EntireOrder {
	
	private @Id @GeneratedValue Integer id;
	private Integer priceHrk;
	private String description;
	private String productName;
	private Integer quantity;
	
	
	public EntireOrder() {}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getPriceHrk() {
		return priceHrk;
	}


	public void setPriceHrk(Integer priceHrk) {
		this.priceHrk = priceHrk;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



}
