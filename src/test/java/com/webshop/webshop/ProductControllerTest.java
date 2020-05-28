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

import com.webshop.webshop.Product.ProductController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {
	

    final String BASE_URL = "http://localhost:8090/";

    @Autowired
    private ProductController controllerTest;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerTest).build();
    }

    @Test
    public void productList() throws Exception{
        controllerTest = mock(ProductController.class);

         this.mockMvc.perform(get("/api/product/list")
                 .accept(MediaType.parseMediaType("application/json")))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void validateProduct() throws Exception {
    	  mockMvc.perform(post("/api/product/validate")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .content("{ \"id\": 1 }") 
    	           .accept(MediaType.APPLICATION_JSON))
    	           .andExpect(status().isOk())
    	           .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void contextLoads() {
    }

}
