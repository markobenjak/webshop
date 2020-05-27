package com.webshop.webshop.Customer;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Customer {
	
	private @Id @GeneratedValue Integer id;
	private String first_name;
	private String last_name;
	private String email;



	private int authority_id;


	private String password;




	//	@ManyToOne
//	@JoinTable(
//			name="Authority",
//			schema = "shop",
//			joinColumns = {@JoinColumn(name="authority_id", referencedColumnName = "id")},
//			inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")}
//	)
	private transient Authority authority;

	
	public Customer() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthority_id() {
		return authority_id;
	}

	public void setAuthority_id(int authority_id) {
		this.authority_id = authority_id;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
}
