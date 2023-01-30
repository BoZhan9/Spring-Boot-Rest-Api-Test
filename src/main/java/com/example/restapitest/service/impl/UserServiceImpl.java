package com.example.restapitest.service.impl;

import com.example.restapitest.pojo.dto.UserResponseDTO;
import com.example.restapitest.pojo.dto.UserResponseDTO.*;
import com.example.restapitest.pojo.entity.User;
import com.example.restapitest.repository.UserRepository;
import com.example.restapitest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Boolean addUser(UserDTO user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setMiddleName(user.getMiddleName());
        newUser.setDob(user.getDob());
        System.out.println(newUser);
        userRepository.saveAndFlush(newUser);
        return true;
    }

    @Override
    public UserResponseDTO getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUserList(allUsers.stream().map(UserDTO::new).collect(Collectors.toList()));
        return responseDTO;
    }
}
