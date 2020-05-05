package com.webshop.webshop;

import static org.mockito.Mockito.mock;
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

import com.webshop.webshop.OrderItem.OrderItemController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderItemControllerTest2 {
	

    final String BASE_URL = "http://localhost:8080/";

    @Autowired
    private OrderItemController controllerTest;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerTest).build();
    }

    @Test
    public void orderItemList() throws Exception{
        controllerTest = mock(OrderItemController.class);

         this.mockMvc.perform(get("/webshop/orderItemList")
                 .accept(MediaType.parseMediaType("application/json")))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void updateOrder() throws Exception {
    	  mockMvc.perform(post("/webshop/updateOrder")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .content("{ \"order_id\": 1,"
    	           		+ " \"product_id\": 1,"
    	           		+ " \"quantity\": 2 }") 
    	           .accept(MediaType.APPLICATION_JSON))
    	           .andExpect(status().isOk());
    }

}
