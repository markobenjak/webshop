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

import com.webshop.webshop.Customer.CustomerController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
	

    final String BASE_URL = "http://localhost:8080/";

    @Autowired
    private CustomerController controllerTest;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerTest).build();
    }

    @Test
    public void customerList() throws Exception{
        controllerTest = mock(CustomerController.class);

         this.mockMvc.perform(get("/webshop/customerList")
                 .accept(MediaType.parseMediaType("application/json")))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void createCustomer() throws Exception {
    	  mockMvc.perform(post("/webshop/createCustomer")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .content("{ \"first_name\": \"first\","
    	           		+ " \"last_name\": \"last\","
    	           		+ " \"email\": \"mail\" }") 
    	           .accept(MediaType.APPLICATION_JSON))
    	           .andExpect(status().isOk());

    }

}
