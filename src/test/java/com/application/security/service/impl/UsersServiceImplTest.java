package com.application.security.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.application.security.entity.Users;
import com.application.security.repository.UsersRepository;

public class UsersServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersServiceImpl usersService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        Users user = new Users();
        user.setUserId("1");
        user.setUserName("John");
        user.setUserEmail("john@example.com");

        Mockito.when(usersRepository.save(Mockito.any(Users.class))).thenReturn(user);

        Optional<Users> result = usersService.addUser(user);

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getUserId());
        assertEquals("John", result.get().getUserName());
        assertEquals("john@example.com", result.get().getUserEmail());

        Mockito.verify(usersRepository, Mockito.times(1)).save(Mockito.any(Users.class));
    }

    @Test
    public void testGetUserById() {
        Users user = new Users();
        user.setUserId("1");
        user.setUserName("John");

        Mockito.when(usersRepository.findById("1")).thenReturn(Optional.of(user));

        Optional<Users> result = usersService.getUserById("1");

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getUserId());
        assertEquals("John", result.get().getUserName());

        Mockito.verify(usersRepository, Mockito.times(1)).findById("1");
    }

    @Test
    public void testGetUserByName() {
        Users user = new Users();
        user.setUserId("1");
        user.setUserName("John");

        Mockito.when(usersRepository.findByUserName("John")).thenReturn(Optional.of(user));

        Optional<Users> result = usersService.getUserByName("John");

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getUserId());
        assertEquals("John", result.get().getUserName());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUserName("John");
    }

    @Test
    public void testGetUserByEmail() {
        Users user = new Users();
        user.setUserId("1");
        user.setUserName("John");
        user.setUserEmail("john@example.com");

        Mockito.when(usersRepository.findByUserEmail("john@example.com")).thenReturn(Optional.of(user));

        Optional<Users> result = usersService.getUserByEmail("john@example.com");

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getUserId());
        assertEquals("John", result.get().getUserName());
        assertEquals("john@example.com", result.get().getUserEmail());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUserEmail("john@example.com");
    }

    @Test
    public void testGetUserByNumber() {
        Users user = new Users();
        user.setUserId("1");
        user.setUserName("John");
        user.setUserMobile(1234567890L);

        Mockito.when(usersRepository.findByUserMobile(1234567890L)).thenReturn(Optional.of(user));

        Optional<Users> result = usersService.getUserByNumber(1234567890L);

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getUserId());
        assertEquals("John", result.get().getUserName());
        assertEquals(1234567890L, result.get().getUserMobile());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUserMobile(1234567890L);
    }

    @Test
    public void testGetUserByRole() {
        Users user1 = new Users();
        user1.setUserId("1");
        user1.setUserName("John");
        user1.setUserRole("ROLE_ADMIN");

        Users user2 = new Users();
        user2.setUserId("2");
        user2.setUserName("Jane");
        user2.setUserRole("ROLE_USER");

        List<Users> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        Mockito.when(usersRepository.findByUserRole("ROLE_ADMIN")).thenReturn(Optional.of(userList));

        Optional<List<Users>> result = usersService.getUserByRole("ROLE_ADMIN");

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertEquals("1", result.get().get(0).getUserId());
        assertEquals("John", result.get().get(0).getUserName());
        assertEquals("ROLE_ADMIN", result.get().get(0).getUserRole());
        assertEquals("2", result.get().get(1).getUserId());
        assertEquals("Jane", result.get().get(1).getUserName());
        assertEquals("ROLE_USER", result.get().get(1).getUserRole());

        Mockito.verify(usersRepository, Mockito.times(1)).findByUserRole("ROLE_ADMIN");
    }
}
