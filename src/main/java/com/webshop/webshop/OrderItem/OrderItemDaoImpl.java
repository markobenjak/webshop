package com.webshop.webshop.OrderItem;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.webshop.webshop.Order.Order;
import com.webshop.webshop.Order.OrderRowMapper;
import com.webshop.webshop.Product.Product;
import com.webshop.webshop.Product.ProductRowMapper;


@Service
public class OrderItemDaoImpl implements OrderItemDao {

	
	public OrderItemDaoImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
	}  

	NamedParameterJdbcTemplate template;  
	
	@Override
	public List<OrderItem> findAll() {
		return template.query("select * from shop.order_item", new OrderItemRowMapper());		
	}
	
	
	@Override
	public void updateOrder(OrderItem orderItem) {

		 final String sql = "insert into shop.order_item(order_id, product_id, quantity) "
				 + "values(:order_id,:product_id,:quantity)";
		 
		 KeyHolder holder = new GeneratedKeyHolder();
		 
		 final String availableProduct="select * from shop.product";
		 ProductRowMapper product = new ProductRowMapper();
		 List<Product> productList = template.query(availableProduct, product);
		 
		 for(int i=0; i<productList.size(); i++) { 
			 if(productList.get(i).getId().equals(orderItem.getProduct_id()) && productList.get(i).is_available()) {
				 
				 SqlParameterSource param = new MapSqlParameterSource()
							.addValue("order_id", orderItem.getOrder_id())
					.addValue("product_id", orderItem.getProduct_id())
					.addValue("quantity", orderItem.getQuantity());
				 template.update(sql,param, holder);
			 }
		 }
	}
}
