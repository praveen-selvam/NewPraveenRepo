package com.autotrek.instantauto.web.controllers;

import com.autotrek.instantauto.services.TokenService;
import com.autotrek.instantauto.test.DealerHelperUtil;
import static com.autotrek.instantauto.test.DealerHelperUtil.createMockDealerForCreate;
import com.autotrek.instantauto.web.dto.DealerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author praveens
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DealerControllerTest {

    @Value("${auth.token.header}")
    private String tokenHeader;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DealerController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getDealerListNoAuth() throws Exception {
        mockMvc.perform(get("/dealer")).andExpect(status().isForbidden());
    }

    @Test
    public void getDealerListAuth() throws Exception {
        String token = tokenService.generateToken("autotrek");
        mockMvc.perform(get("/dealer").header(tokenHeader, token))
                .andExpect(status().isOk());
    }

    @Test
    public void createDealerNoAuth() throws Exception {
        DealerDto dto = DealerHelperUtil.createMockDealerForCreate();
        mockMvc.perform(
                post("/dealer")
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    public void createDealerWithAuth() throws Exception {
        DealerDto dto = createMockDealerForCreate();
        String token = tokenService.generateToken("autotrek");
        mockMvc.perform(
                post("/dealer")
                        .header(tokenHeader, token)
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void createDealerDuplicate() throws Exception {
        DealerDto dto = createMockDealerForCreate();
        String token = tokenService.generateToken("autotrek");
        mockMvc.perform(
                post("/dealer")
                        .header(tokenHeader, token)
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isCreated());

        mockMvc.perform(
                post("/dealer")
                        .header(tokenHeader, token)
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isConflict());
    }

    @Test
    public void createDealerMissingDealerName() throws Exception {
        DealerDto dto = createMockDealerForCreate();
        dto.dealerName = null;
        String token = tokenService.generateToken("autotrek");
        mockMvc.perform(
                post("/dealer")
                        .header(tokenHeader, token)
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isBadRequest());
    }
}
