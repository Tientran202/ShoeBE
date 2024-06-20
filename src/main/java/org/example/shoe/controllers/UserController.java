package org.example.shoe.controllers;

import org.example.shoe.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "shoe/api/user")
public class UserController {
    @Autowired
    UserService userService;

}
