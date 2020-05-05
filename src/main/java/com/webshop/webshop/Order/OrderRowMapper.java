package com.webshop.webshop.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order>{

	@Override
	public Order mapRow(ResultSet rs, int arg1) throws SQLException {
		Order order = new Order();
		
		order.setId(rs.getInt("id"));
		order.setCustomer_id(rs.getInt("customer_id"));
		order.setStatus(rs.getString("status"));
		order.setTotal_price_hrk(rs.getInt("total_price_hrk"));
		order.setTotal_price_eur(rs.getInt("total_price_eur"));
		return order;

	}

}
