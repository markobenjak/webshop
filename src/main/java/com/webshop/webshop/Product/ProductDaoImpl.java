package com.webshop.webshop.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class ProductDaoImpl implements ProductDao {

	public ProductDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
	}  

	NamedParameterJdbcTemplate template;

	@Override
	public Product singleProduct(int productId){

		final String productSql = "select * from shop.product where id = :id limit 1";
		SqlParameterSource productParam = new MapSqlParameterSource()
				.addValue("id", productId);

		ProductRowMapper productMaper = new ProductRowMapper();
		List<Product> itemList = template.query(productSql, productParam ,productMaper);

		if(itemList.size() > 0) return itemList.get(0);
		return null;
	}
	
	@Override
	public List<Product> findAll() {
		return template.query("select * from shop.product", new ProductRowMapper());		
	}

	@Override
	public void createProduct(Product product) {
		final String sql = "insert into shop.product(code, name, price_hrk, description, is_available) "
				+ "values(:code, :name, :price_hrk, :description, :is_available)";

		KeyHolder holder = new GeneratedKeyHolder();

		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("code", product.getCode())
				.addValue("name", product.getName())
				.addValue("price_hrk", product.getPrice_hrk())
				.addValue("description", product.getDescription())
				.addValue("is_available", product.is_available());
		template.update(sql,param, holder);
	}

	@Override
	public void updateProduct(Product product) {
		final String sql = "update shop.product " +
				"set code = :code, " +
				"name = :name, " +
				"price_hrk = :price_hrk, " +
				"description = :description, " +
				"is_available =  :is_available " +
				"where id = :id";

		KeyHolder holder = new GeneratedKeyHolder();

		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("id", product.getId())
				.addValue("code", product.getCode())
				.addValue("name", product.getName())
				.addValue("price_hrk", product.getPrice_hrk())
				.addValue("description", product.getDescription())
				.addValue("is_available", product.is_available());
		template.update(sql,param, holder);
	}
	
	@Override
	public String validateProduct(int productId) {
		
		final String productSql = "select * from shop.product where id = :id";
		SqlParameterSource productParam = new MapSqlParameterSource()
				.addValue("id", productId);
		
		ProductRowMapper productMaper = new ProductRowMapper();
		List<Product> itemList = template.query(productSql, productParam ,productMaper);
		
		if(itemList.get(0).is_available()) {
			return "Product " + itemList.get(0).getName() + " is available";
		}else {
			return "Product " + itemList.get(0).getName() + " is not available";
		}
	}

	@Override
	public void deleteProduct(int productId) {
		final String sql = "delete from shop.product where id=:id";

		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("id", productId);

		template.execute(sql,map,new PreparedStatementCallback<Integer>() {
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				return ps.executeUpdate();
			}
		});
	}

	@Override
	public List<Product> searchProduct(String searchString) {

		final String productSql = "select * from shop.product where upper(name||' '||description) like upper('%'||:searchString||'%')";

		SqlParameterSource productParam = new MapSqlParameterSource()
				.addValue("searchString", searchString);

		ProductRowMapper productMaper = new ProductRowMapper();
		return template.query(productSql, productParam ,productMaper);
	}
}
