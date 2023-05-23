package com.example.demo_2305.domain.dao;

import com.example.demo_2305.domain.UserDTO;
import org.springframework.security.core.userdetails.User;

public interface IUserService {
    User registerUser(UserDTO userDTO);
}
