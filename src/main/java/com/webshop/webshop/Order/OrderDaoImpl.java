package com.webshop.webshop.Order;

import com.webshop.webshop.OrderItem.OrderItem;
import com.webshop.webshop.OrderItem.OrderItemRowMapper;
import com.webshop.webshop.Product.Product;
import com.webshop.webshop.Product.ProductRowMapper;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OrderDaoImpl implements OrderDao {

	NamedParameterJdbcTemplate template;
	JdbcTemplate jdbcTemplate;

	public OrderDaoImpl(NamedParameterJdbcTemplate template, JdbcTemplate jdbcTemplate) {
		this.template = template;
		this.jdbcTemplate = jdbcTemplate;
	}

	public enum status{
		DRAFT,
		SUBMITTED
	}


	
	@Override
	public List<Order> findAll() {
		return template.query("SELECT * FROM shop.webshop_order", new OrderRowMapper());
	}
	
	@Override
	public void createOrder(Order order) {

		 final String sql = "INSERT INTO shop.webshop_order(customer_id, status, total_price_hrk, total_price_eur) "
				+ "VALUES(:customer_id,:status,:total_price_hrk,:total_price_eur)";

		 KeyHolder holder = new GeneratedKeyHolder();

		 SqlParameterSource param = new MapSqlParameterSource()
			.addValue("customer_id", order.getCustomerId())
			.addValue("status", status.DRAFT.toString())
			.addValue("total_price_hrk", order.getTotalPriceHrk())
			.addValue("total_price_eur", order.getTotalPriceEur());
		 template.update(sql,param, holder);

	}
	
	@Override
	public List<EntireOrder> readOrder(Integer customerId){
		
		final String sql = "SELECT wo.id, p.name, p.description, p.price_hrk, oi.quantity "
				+ "FROM shop.webshop_order wo "
				+ "JOIN shop.order_item oi ON wo.id = oi.order_id "
				+ "JOIN shop.product p ON p.id = oi.product_id "
				+ "WHERE wo.customer_id = :customer_id";
		
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("customer_id", customerId);
		List<EntireOrder> entireOrderList = template.query(sql, param, new EntireOrderRowMapper());
		
		
		if(entireOrderList.size() > 0) return entireOrderList;
		return null;
	}
	
	@Override
	public void deleteOrder(int orderId) {
		 final String sql = "DELETE FROM shop.webshop_order WHERE id=:id";
		 
		 Map<String,Integer> map=new HashMap<String,Integer>();
		 map.put("id", orderId);
		 
		 template.execute(sql,map,new PreparedStatementCallback<Integer>() {
			 @Override  
			 public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				 return ps.executeUpdate();  
				 }  
		 });  
	}
	
	@Override
	public void finalizeOrder(Order order){
		Double totalPriceHRK = 0.0;
		Double totalPriceEUR = 0.0;
		Integer quantity;
		Integer productId;
		final String itemsSql = "SELECT * FROM shop.order_item "
				+ "WHERE order_id = (SELECT id FROM shop.webshop_order WHERE customer_id = :customer_id "
				+ "AND status = '" + status.DRAFT.toString() + "' LIMIT 1)";
		SqlParameterSource paramItems = new MapSqlParameterSource()
				.addValue("customer_id", order.getCustomerId());
		
		OrderItemRowMapper orderItem = new OrderItemRowMapper();
		List<OrderItem> itemList = template.query(itemsSql, paramItems ,orderItem);
		
		for(int i=0; i<itemList.size();i++) {
			quantity = itemList.get(i).getQuantity();
			productId = itemList.get(i).getProduct_id();
			
			final String productSql = "SELECT * FROM shop.product WHERE id = :product_id";
			SqlParameterSource paramProduct = new MapSqlParameterSource()
					.addValue("product_id", productId);
			ProductRowMapper  product = new ProductRowMapper();
			List<Product> productList = template.query(productSql, paramProduct ,product);
			
			totalPriceHRK = totalPriceHRK + (productList.get(0).getPrice_hrk() * quantity);			
		}
		
		try {
			totalPriceEUR = totalPriceHRK / exchangeRate();
			BigDecimal bd = new BigDecimal(totalPriceEUR).setScale(2, RoundingMode.HALF_UP);
			totalPriceEUR = bd.doubleValue();
			
			bd = new BigDecimal(totalPriceHRK).setScale(2, RoundingMode.HALF_UP);
			totalPriceHRK = bd.doubleValue();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final String sql = "UPDATE shop.webshop_order "
				+ "SET status='"+ status.SUBMITTED.toString() +"', total_price_hrk = :totalPriceHRK, total_price_eur = :totalPriceEUR "
				+ "WHERE customer_id=:customer_id";
		KeyHolder holder = new GeneratedKeyHolder();

		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("customer_id", order.getCustomerId())
				.addValue("totalPriceHRK", totalPriceHRK)
				.addValue("totalPriceEUR", totalPriceEUR);

		template.update(sql,param, holder);

	}
	
	public Double exchangeRate() throws IOException {

		InputStream is = new URL("http://api.hnb.hr/tecajn/v2?valuta=EUR").openStream();
		String srednjiTecaj;
	    try {
	    	
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String jsonText = readAll(rd);
		jsonText = jsonText.replace("[", "");
		jsonText = jsonText.replace("]", "");
		JSONObject json = new JSONObject(jsonText);

		srednjiTecaj = json.getString("srednji_tecaj");
		srednjiTecaj = srednjiTecaj.replace(",", ".");
	      
	    } finally {
	      is.close();
	    }
		return Double.parseDouble(srednjiTecaj);
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	  }

	  public Integer countSubmittedOrders(){
		int submittedOrders = 0;
		final String sqlSubmittedOrders = "SELECT count(id) "
				  + "FROM shop.webshop_order "
				  + "WHERE status = 'SUBMITTED'; ";

		return jdbcTemplate.queryForObject(sqlSubmittedOrders, Integer.class);

	  }

	public Integer countDraftOrders(){
		int draftOrders = 0;
		final String sqlDraftOrders = "SELECT count(id) "
				+ "FROM shop.webshop_order "
				+ "WHERE status = 'DRAFT'; ";

		return jdbcTemplate.queryForObject(sqlDraftOrders, Integer.class);

	}


	
}
