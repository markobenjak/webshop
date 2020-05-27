package com.webshop.webshop.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.webshop.webshop.Product.Product;
import com.webshop.webshop.Product.ProductRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
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
	public Customer FindCustomerById(int customerId){
		final String customerSql = "select * from shop.customer c " +
				"inner join shop.authority a on a.id = c.authority_id where c.id = :id limit 1";
		SqlParameterSource customerParam = new MapSqlParameterSource()
				.addValue("id", customerId);

		CustomerRowMapper customerMapper = new CustomerRowMapper();
		List<Customer> itemList = template.query(customerSql, customerParam ,customerMapper);

		if(itemList.size() > 0) return itemList.get(0);
		return null;
	}

	@Override
	public Optional<Customer> FindCustomerByEmail(String email){
		final String customerSql = "select * from shop.customer c " +
				"inner join shop.authority a on a.id = c.authority_id where c.email = :email limit 1";
		SqlParameterSource customerParam = new MapSqlParameterSource()
				.addValue("email", email);

		CustomerRowMapper customerMapper = new CustomerRowMapper();
		List<Customer> itemList = template.query(customerSql, customerParam ,customerMapper);

		if(itemList.size() > 0) return Optional.of(itemList.get(0));
		return null;
	}
	
	@Override
	public List<Customer> FindAll() {
		return template.query("select * from shop.customer", new CustomerRowMapper());		
	}

	@Override
	public Customer CreateCustomer(Customer customer) {
		
		final String sql = "insert into shop.customer (first_name, last_name, email) "
				+ " values(:first_name, :last_name, :email) returning id;";
		 
		 KeyHolder holder = new GeneratedKeyHolder();

		 SqlParameterSource param = new MapSqlParameterSource()
			.addValue("first_name", customer.getFirst_name())
			.addValue("last_name", customer.getLast_name())
			.addValue("email", customer.getEmail());
		 template.update(sql,param, holder);

		 customer.setId(holder.getKey().intValue());

		 return customer;
	}

	@Override
	public void UpdateCustomer(Customer customer){
		final String sql = "update shop.customer set first_name = :first_name, last_name = :last_name, email = :email where id = :id";

		SqlParameterSource params = new MapSqlParameterSource()
										.addValue("id", customer.getId())
										.addValue("first_name", customer.getFirst_name())
										.addValue("last_name", customer.getLast_name())
										.addValue("email", customer.getEmail());
		KeyHolder holder = new GeneratedKeyHolder();

		template.update(sql, params, holder);
	}

	@Override
	public void DeleteCustomer(int customerId){
		final String sql = "delete from shop.customer where id=:id";

		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("id", customerId);

		template.execute(sql,map,new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				return ps.executeUpdate();
			}
		});
	}

}
