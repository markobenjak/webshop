package com.webshop.webshop.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
		Customer customer = new Customer();
		
		customer.setId(rs.getInt("id"));
		customer.setFirst_name(rs.getString("first_name"));
		customer.setLast_name(rs.getString("last_name"));
		customer.setEmail(rs.getString("email"));
		return customer;

	}

}
