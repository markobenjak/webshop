package com.webshop.webshop;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.webshop.webshop.Order.OrderController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {
	

    final String BASE_URL = "http://localhost:8080/";
    private RestTemplate restTemplate;
    private WireMockServer wireMockServer;
    
    @Autowired
    private OrderController controllerTest;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerTest).build();
    }

    @Test
    public void orderList() throws Exception{
        controllerTest = mock(OrderController.class);

         this.mockMvc.perform(get("/webshop/orderList")
                 .accept(MediaType.parseMediaType("application/json")))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void createOrder(){
    	  try {
			mockMvc.perform(post("/webshop/createOrder")
			           .contentType(MediaType.APPLICATION_JSON)
			           .content("{ \"customer_id\": 1,"
			           		+ " \"total_price_hrk\": 100.00,"
			           		+ " \"total_price_eur\": 100.00 }") 
			           .accept(MediaType.APPLICATION_JSON))
			           .andExpect(status().isOk());
		} catch (Exception e) {
			System.out.println(e);
		}

    }
    
    @Test
    public void radOrder() throws Exception {
    	  mockMvc.perform(post("/webshop/readOrder")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .content("{ \"customer_id\": 1 }") 
    	           .accept(MediaType.APPLICATION_JSON))
    	           .andExpect(status().isOk())
    	           .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
    
    @Test
    public void deleteOrder() throws Exception {
    	  mockMvc.perform(delete("/webshop/deleteOrderById")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .content("{ \"id\": 15 }") 
    	           .accept(MediaType.APPLICATION_JSON))
    	           .andExpect(status().isOk());

    }
    
    @Test
    public void finalizeOrder() throws Exception {
    	  mockMvc.perform(post("/webshop/finalizeOrder")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .content("{ \"customer_id\": 1 }") 
    	           .accept(MediaType.APPLICATION_JSON))
    	           .andExpect(status().isOk());

    }

    
    

    @Test
    public void contextLoads() {
    }

}
