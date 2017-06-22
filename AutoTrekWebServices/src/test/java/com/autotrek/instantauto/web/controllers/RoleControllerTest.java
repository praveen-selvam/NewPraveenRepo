package com.autotrek.instantauto.web.controllers;

import com.autotrek.instantauto.services.TokenService;
import com.autotrek.instantauto.test.RoleHelperUtil;
import static com.autotrek.instantauto.test.RoleHelperUtil.*;
import com.autotrek.instantauto.web.dto.RoleDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author praveens
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RoleControllerTest {

    @Value("${auth.token.header}")
    private String tokenHeader;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void getRoleListNoAuth() throws Exception {
        mockMvc.perform(get("/role")).andExpect(status().isForbidden());
    }

    @Test
    public void getRoleListAuth() throws Exception {
        String token = tokenService.generateToken("autotrek");
        mockMvc.perform(get("/role").header(tokenHeader, token))
                .andExpect(status().isOk());
    }

    @Test
    public void createRoleNoAuth() throws Exception {
        RoleDto dto = RoleHelperUtil.createMockRoleForCreate();
        mockMvc.perform(
                post("/role")
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isForbidden());
    }

    @Test
    public void createRoleWithAuth() throws Exception {
        RoleDto dto = createMockRoleForCreate();
        String token = tokenService.generateToken("autotrek");
        mockMvc.perform(
                post("/role")
                        .header(tokenHeader, token)
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void createRoleDuplicate() throws Exception {
        RoleDto dto = createMockRoleForCreate();
        String token = tokenService.generateToken("autotrek");
        mockMvc.perform(
                post("/role")
                        .header(tokenHeader, token)
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isCreated());

        mockMvc.perform(
                post("/role")
                        .header(tokenHeader, token)
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isConflict());
    }

    @Test
    public void createRoleMissingRoleName() throws Exception {
        RoleDto dto = createMockRoleForCreate();
        dto.roleName = null;
        String token = tokenService.generateToken("autotrek");
        mockMvc.perform(
                post("/role")
                        .header(tokenHeader, token)
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testEditRole() throws Exception {
        RoleDto dto = editMockRoleForEdit();

        String token = tokenService.generateToken("autotrek");
        mockMvc.perform(
                put("/role/1")
                        .header(tokenHeader, token)
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isOk());
    }

}
