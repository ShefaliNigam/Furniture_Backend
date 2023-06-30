package com.application.security.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.application.security.entity.Users;
import com.application.security.service.impl.UsersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

class UsersControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsersServiceImpl usersService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }


    @Test
    void testGetUserById() throws Exception {
        Users user = new Users();
        user.setUserId("1");
        user.setUserName("John");

        when(usersService.getUserById("1")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/id/1"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    Users responseUser = new ObjectMapper().readValue(response, Users.class);
                    assertEquals("1", responseUser.getUserId());
                    assertEquals("John", responseUser.getUserName());
                });

        verify(usersService).getUserById("1");
    }

    @Test
    void testGetUserByName() throws Exception {
        Users user = new Users();
        user.setUserId("1");
        user.setUserName("John");

        when(usersService.getUserByName("John")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/name/").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    Users responseUser = new ObjectMapper().readValue(response, Users.class);
                    assertEquals("1", responseUser.getUserId());
                    assertEquals("John", responseUser.getUserName());
                });

        verify(usersService).getUserByName("John");
    }

    @Test
    void testGetUserByEmail() throws Exception {
        Users user = new Users();
        user.setUserId("1");
        user.setUserName("John");
        user.setUserEmail("john@example.com");

        when(usersService.getUserByEmail("john@example.com")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/email/").param("email", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    Users responseUser = new ObjectMapper().readValue(response, Users.class);
                    assertEquals("1", responseUser.getUserId());
                    assertEquals("John", responseUser.getUserName());
                    assertEquals("john@example.com", responseUser.getUserEmail());
                });

        verify(usersService).getUserByEmail("john@example.com");
    }



    @Test
    void testGetUserByNumber() throws Exception {
        Users user = new Users();
        user.setUserId("1");
        user.setUserName("John");
        user.setUserMobile(1234567890L);

        when(usersService.getUserByNumber(1234567890L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/mobile/").param("mobile", "1234567890"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    Users responseUser = new ObjectMapper().readValue(response, Users.class);
                    assertEquals("1", responseUser.getUserId());
                    assertEquals("John", responseUser.getUserName());
                    assertEquals(1234567890L, responseUser.getUserMobile());
                });

        verify(usersService).getUserByNumber(1234567890L);
    }

    @Test
    void testHome() throws Exception {
        mockMvc.perform(get("/user/home"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    assertEquals("This is Home Page", response);
                });
    }
}
