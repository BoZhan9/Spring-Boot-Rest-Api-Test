package com.example.restapitest.service.impl;

import com.example.restapitest.controller.UserController;
import com.example.restapitest.pojo.dto.UserResponseDTO;
import com.example.restapitest.pojo.dto.UserResponseDTO.*;
import com.example.restapitest.repository.UserRepository;
import com.example.restapitest.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private MockMvc mockMvc;
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        //userService = new UserService(userRepository);
    }

    @Test
    void addUser() throws Exception {
        UserDTO testDTO = new UserDTO("Kobe", "Bryant", "Bean", "1978-08-23");
        when(userService.addUser(testDTO)).thenReturn(true);
        Map<String, UserDTO> resForm = new HashMap<>();
        resForm.put("provider", testDTO);
        MvcResult mvcRes = mockMvc.perform(MockMvcRequestBuilders.post("/userinfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(resForm)))
                .andReturn();

        assertEquals(201, mvcRes.getResponse().getStatus());
    }
}

class AddUserOperation {
    void addUser(UserDTO userDTO) {
        if (userDTO.getFirstName() == null || userDTO.getFirstName().equals("")) {
            throw new IllegalArgumentException("input firstName is invalid");
        }
        if (userDTO.getLastName() == null || userDTO.getLastName().equals("")) {
            throw new IllegalArgumentException("input lastName is invalid");
        }
        if (userDTO.getDob() == null || userDTO.getDob().equals("")) {
            throw new IllegalArgumentException("input dob is invalid");
        }
    }
}

class AddUserUnitTest {
    private static AddUserOperation addUserOperation;

    @BeforeAll
    public static void init() {
        addUserOperation = new AddUserOperation();
    }

    @Test
    public void invalidInput() {
        assertThrows(IllegalArgumentException.class,
                () -> addUserOperation.addUser(new UserDTO("", "", "", "")));
    }
}