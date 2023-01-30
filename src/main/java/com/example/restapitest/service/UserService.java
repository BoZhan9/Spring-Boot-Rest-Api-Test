package com.example.restapitest.service;

import com.example.restapitest.pojo.dto.UserResponseDTO;
import com.example.restapitest.pojo.dto.UserResponseDTO.*;
import com.example.restapitest.pojo.entity.User;


public interface UserService {

    Boolean addUser(UserDTO user);

    UserResponseDTO getAllUsers();


}
