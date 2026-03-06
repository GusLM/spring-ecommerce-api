package com.gusdev.spring_ecommerce_api.services;

import com.gusdev.spring_ecommerce_api.entities.User;
import com.gusdev.spring_ecommerce_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
