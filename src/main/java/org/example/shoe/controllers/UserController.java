package org.example.shoe.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.shoe.dto.UserDto;
import org.example.shoe.entity.User;
import org.example.shoe.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpSession session){
        if(userService.login(userDto)){
            User user = userService.findByEmail(userDto.getEmail());
            session.setAttribute("username",user.getName());
            session.setAttribute("email",user.getEmail());
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.ok("Login unsuccessful");
    }

}
