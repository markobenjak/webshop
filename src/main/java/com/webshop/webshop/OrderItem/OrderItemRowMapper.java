package com.webshop.webshop.OrderItem;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderItemRowMapper implements RowMapper<OrderItem>{

	@Override
	public OrderItem mapRow(ResultSet rs, int arg1) throws SQLException {
		OrderItem orderItem = new OrderItem();
		
		orderItem.setId(rs.getInt("id"));
		orderItem.setOrder_id(rs.getInt("order_id"));
		orderItem.setProduct_id(rs.getInt("product_id"));
		orderItem.setQuantity(rs.getInt("quantity"));

		return orderItem;

	}

}
