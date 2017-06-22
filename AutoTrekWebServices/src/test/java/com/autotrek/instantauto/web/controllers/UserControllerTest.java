package com.autotrek.instantauto.web.controllers;

import com.autotrek.instantauto.services.TokenService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Joe C. McPherson
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Value("${auth.token.header}")
    private String tokenHeader;

    @Autowired
    private UserController controller;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getUserListNoAuth() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isForbidden());
    }

    @Test
    public void getUserListAuth() throws Exception {
        String token = tokenService.generateToken("autotrek");
        mockMvc.perform(get("/user").header(tokenHeader, token))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserNoAuth() throws Exception {
        mockMvc.perform(get("/user/1")).andExpect(status().isForbidden());
    }

    @Test
    public void getUserAuth() throws Exception {
        String token = tokenService.generateToken("autotrek");
        mockMvc.perform(get("/user/1").header(tokenHeader, token))
                .andExpect(status().isOk());
    }
}
