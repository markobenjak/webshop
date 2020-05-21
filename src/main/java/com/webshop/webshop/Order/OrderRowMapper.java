package com.webshop.webshop.Order;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order>{

	@Override
	public Order mapRow(ResultSet rs, int arg1) throws SQLException {
		Order order = new Order();
		
		order.setId(rs.getInt("id"));
		order.setCustomerId(rs.getInt("customer_id"));
		order.setStatus(rs.getString("status"));
		order.setTotalPriceHrk(rs.getInt("total_price_hrk"));
		order.setTotalPriceEur(rs.getInt("total_price_eur"));
		return order;

	}

}
