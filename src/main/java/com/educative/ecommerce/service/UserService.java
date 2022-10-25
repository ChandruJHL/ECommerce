package com.educative.ecommerce.service;


import com.educative.ecommerce.dto.UserDto;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(@Valid User user)
    {
        userRepository.save(user);
    }

    public Optional<User> readUserMail(Long mailId)
    {
        return userRepository.findById(mailId);
    }

    public String ValidateUser(String email, String password)
    {
            String responseMessage = null;
            User user = userRepository.findByEmail(email);
            String storedEmail = user.getEmail();
            String storedPassword = user.getPassword();
            if (storedEmail.equals(email) && storedPassword.equals(password))
            {
                responseMessage = " UserName and Password correct";
            } else
            {
                responseMessage = "UserName and Password incorrect";
            }
             return responseMessage;
    }
}