package com.thu.movie.controller;

import com.thu.movie.DTO.UserDto;
import com.thu.movie.model.ResponseObject;
import com.thu.movie.model.User;
import com.thu.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> createNewUser(@RequestBody UserDto newUser) {
        User user = userService.registerNewUserAccount(newUser);
        ResponseObject response = new ResponseObject(
                "OK",
                "OK",
                user
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
