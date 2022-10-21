package com.educative.ecommerce.service;


import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user)
    {

        userRepository.save(user);
    }

    public Optional<User> readUserMail(Long mailId)
    {
        return userRepository.findById(mailId);
    }

    public String ValidateUser(String email, String password)
    {
        String responseMessage ;
        User user = userRepository.findById(Long.valueOf(email)).get();
        String storedPassword  = user.getPassword();
        String storedEmail = user.getEmail();
        if ( storedEmail == email && storedPassword == password)
        {
            responseMessage = " UserName and Password correct";
        }else
        {
            responseMessage = "UserName and Password incorrect";
        }
        return responseMessage;
    }
}
