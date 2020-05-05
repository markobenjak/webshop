package com.webshop.webshop.Order;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Order {
	
	private @Id @GeneratedValue Integer id;
	private Integer customer_id;
	private Integer total_price_hrk;
	private Integer total_price_eur;
	private String status;
	
	
	public Order() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getTotal_price_hrk() {
		return total_price_hrk;
	}

	public void setTotal_price_hrk(Integer total_price_hrk) {
		this.total_price_hrk = total_price_hrk;
	}

	public Integer getTotal_price_eur() {
		return total_price_eur;
	}

	public void setTotal_price_eur(Integer total_price_eur) {
		this.total_price_eur = total_price_eur;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
