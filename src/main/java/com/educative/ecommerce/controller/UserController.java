package com.educative.ecommerce.controller;

import com.educative.ecommerce.common.ApiResponse;
import com.educative.ecommerce.dto.UserDto;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/UserDetails")
public class UserController {


    @Autowired
    private UserService userService;
    @PostMapping("/createUser")

    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody User user) {
       /* if (Objects.nonNull(userService.readUserMail(user.getId()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "User already exists"), HttpStatus.CONFLICT);
        }*/
        userService.createUser(user);
        return new ResponseEntity<>(new ApiResponse(true, "created the user"), HttpStatus.CREATED);
    }
    @PostMapping("/loginUser")
    public ResponseEntity<ApiResponse> loginUser(@Valid @RequestBody UserDto user){
        String email = user.getEmail();
        String password = user.getPassword();
        String responseMessage = userService.ValidateUser(email,password);
        if(responseMessage != "")
        {
            return new ResponseEntity<>(new ApiResponse(true, responseMessage), HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, responseMessage), HttpStatus.CONFLICT);
        }
    }
}
