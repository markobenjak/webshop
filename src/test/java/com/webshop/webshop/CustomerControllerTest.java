package com.webshop.webshop;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.webshop.webshop.Customer.Customer;
import com.webshop.webshop.Customer.CustomerDao;
import com.webshop.webshop.Customer.CustomerDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.webshop.webshop.Customer.CustomerController;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
	

    final String BASE_URL = "http://localhost:8080/";

    final String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYXJrZXppLmJvcm5hQGdtYWlsLmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE1OTA2MDgyMTB9.C8xLFqDbcKG9alkwT3kXXbf7viVGq-seIi9P8bo5XG2sYe06g7D9Qtwm1d9q8GslMWBuS1muJ4Tj1Qcj077lxA";

    @Autowired
    private CustomerController controllerTest;

    CustomerDao customerDao;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                                .standaloneSetup(controllerTest)
                                //.apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                                .build();
    }

    @Test
    public void customerList() throws Exception{
        controllerTest = mock(CustomerController.class);

         this.mockMvc.perform(get("/api/customer/all")
                 .header("Bearer ", jwtToken)
                 .accept(MediaType.parseMediaType("application/json")))
                 .andExpect(status().isUnauthorized())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void createCustomer() throws Exception {
    	  mockMvc.perform(post("/api/customer")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .content("{ \"id\":999,\"first_name\": \"first\","
    	           		+ " \"last_name\": \"last\","
    	           		+ " \"email\": \"mail_test\" }")
    	           .accept(MediaType.APPLICATION_JSON))
    	           .andExpect(status().isOk());
    }

    @Test
    public void getCustomer() throws Exception {
        //Customer customer = customerDao.FindCustomerByEmail("mail_test").get();

        mockMvc.perform(get("/api/customer/{id}", 999)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCustomer() throws Exception {
        //Customer customer = customerDao.FindCustomerByEmail("mail_test").get();

        mockMvc.perform(put("/api/customer/{id}", 999)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"first_name\": \"first\","
                        + " \"last_name\": \"last\","
                        + " \"email\": \"mail_test2\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteCustomer() throws Exception {
        //Customer customer = customerDao.FindCustomerByEmail("mail_test").get();

        mockMvc.perform(delete("/api/customer/{id}", 999)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
