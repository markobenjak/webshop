package com.webshop.webshop.Customer;

import java.util.List;

import com.webshop.webshop.Product.Product;
import com.webshop.webshop.Product.ProductRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;


@Service
public class CustomerDaoImpl implements CustomerDao {

	
	public CustomerDaoImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
	}  

	NamedParameterJdbcTemplate template;

	@Override
	public Customer findCustomerById(int customerId){
		final String customerSql = "select * from shop.customer where id = :id limit 1";
		SqlParameterSource customerParam = new MapSqlParameterSource()
				.addValue("id", customerId);

		CustomerRowMapper customerMapper = new CustomerRowMapper();
		List<Customer> itemList = template.query(customerSql, customerParam ,customerMapper);

		if(itemList.size() > 0) return itemList.get(0);
		return null;
	}
	
	@Override
	public List<Customer> findAll() {
		return template.query("select * from shop.customer", new CustomerRowMapper());		
	}

	@Override
	public void createCustomer(Customer customer) {
		
		final String sql = "insert into shop.customer (first_name, last_name, email) "
				+ " values(:first_name, :last_name, :email) returning id;";
		 
		 KeyHolder holder = new GeneratedKeyHolder();

		 SqlParameterSource param = new MapSqlParameterSource()
			.addValue("first_name", customer.getFirst_name())
			.addValue("last_name", customer.getLast_name())
			.addValue("email", customer.getEmail());
		 template.update(sql,param, holder);
	}
	

}
