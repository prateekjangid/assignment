package com.amdocs.media.assignment.controller;


import java.util.ArrayList;


import org.springframework.security.core.userdetails.User;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.amdocs.media.assignement.service.AuthenticationService;

import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean 
    private AuthenticationService authServiceMock;
    
    @Test
    public void should_login_When_ValidRequest() throws Exception {
     when(authServiceMock.loadUserByUsername("user")).thenReturn(new User("user","1234",new ArrayList<>()));
      mockMvc.perform(post("/login")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{ \"username\": \"user\", \"password\": \"1234\" }") 
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().is2xxSuccessful())
               .andExpect(jsonPath("$.token").value("user"));
    }
    
    @Test
    public void shouldNot_login_When_InvalidRequest() throws Exception {
         when(authServiceMock.loadUserByUsername("user")).thenReturn(new User("user","1234",new ArrayList<>()));
          mockMvc.perform(post("/login")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content("{ \"username\": \"user\", \"password\": \"1234567\" }")
                   .accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().isBadRequest());
        }
    
}
 








