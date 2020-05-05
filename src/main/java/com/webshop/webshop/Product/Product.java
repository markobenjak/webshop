package com.webshop.webshop.Product;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class Product {
	
	private @Id @GeneratedValue Integer id;
	private String code;
	private String name;
	private Integer price_hrk;
	private String description;
	private boolean is_available;
	
	public Product() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice_hrk() {
		return price_hrk;
	}

	public void setPrice_hrk(Integer price_hrk) {
		this.price_hrk = price_hrk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty(value="isAvailable")
	public boolean is_available() {
		return is_available;
	}

	public void setIs_available(boolean is_available) {
		this.is_available = is_available;
	}

}
