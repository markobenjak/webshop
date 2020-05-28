package com.webshop.webshop;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.webshop.webshop.Customer.CustomerController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.webshop.webshop.auth.AuthController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
    final String BASE_URL = "http://localhost:8080/";

    @Autowired
    private AuthController controllerTest;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerTest).build();
    }

    @Test
    public void authSuccessTest() throws Exception{
        controllerTest = mock(AuthController.class);

        this.mockMvc.perform(post("/api/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"sarkezi.borna@gmail.com\",\"password\":\"pass1234\", \"remember\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void authFailTest() throws Exception{
        controllerTest = mock(AuthController.class);

        try{
            this.mockMvc.perform(post("/api/auth/authenticate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"username\":\"sarkezi.borna@gmail.com\",\"password\":\"krivalozinka\", \"remember\":true}")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
        catch(BadCredentialsException e){
            System.out.println(e.getMessage());
        }

    }
}
