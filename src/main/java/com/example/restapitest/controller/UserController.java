package com.example.restapitest.controller;

import com.example.restapitest.pojo.dto.UserRequestDTO;
import com.example.restapitest.pojo.dto.UserResponseDTO;
import com.example.restapitest.pojo.dto.UserResponseDTO.*;
import com.example.restapitest.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userinfo")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserDTO requestDTO = userRequestDTO.getProvider();
            Boolean res = userService.addUser(requestDTO);
            if (res) {
                UserDTO responseDTO = new UserDTO();
                responseDTO.setFirstName(userRequestDTO.getProvider().getFirstName());
                responseDTO.setLastName(userRequestDTO.getProvider().getLastName());
                responseDTO.setMiddleName(userRequestDTO.getProvider().getMiddleName());
                responseDTO.setDob(userRequestDTO.getProvider().getDob());
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
