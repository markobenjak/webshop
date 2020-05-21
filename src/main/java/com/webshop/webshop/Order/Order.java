package com.webshop.webshop.Order;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Order {
	
	private @Id @GeneratedValue Integer id;
	private Integer customerId;
	private Integer totalPriceHrk;
	private Integer totalPriceEur;
	private String status;
	
	
	public Order() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getTotalPriceHrk() {
		return totalPriceHrk;
	}

	public void setTotalPriceHrk(Integer totalPriceHrk) {
		this.totalPriceHrk = totalPriceHrk;
	}

	public Integer getTotalPriceEur() {
		return totalPriceEur;
	}

	public void setTotalPriceEur(Integer totalPriceEur) {
		this.totalPriceEur = totalPriceEur;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
