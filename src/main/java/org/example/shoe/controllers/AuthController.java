package org.example.shoe.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.shoe.dto.UserDto;
import org.example.shoe.entity.PasswordChangeRequest;
import org.example.shoe.entity.User;
import org.example.shoe.sevices.EmailService;
import org.example.shoe.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(path = "shoe/api/auth")
public class AuthController {
    @Autowired
    EmailService emailService;
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        // Check if the account gmail exists
        if (userService.existByEmail(userDto.getEmail())) {
            return ResponseEntity.ok("Email exist");
        }
        String subject = "Email authentication";

        String confirmationUrl = "http://" + request.getServerName() + ":" + request.getServerPort() +
                "/api/auth/registration-confirmation?name=" + userDto.getName() + "&password=" + userDto.getPassword() + "&email=" + userDto.getEmail();

        try {
            emailService.sendCustomEmail(userDto.getName(), userDto.getPassword(), userDto.getEmail(), subject, confirmationUrl);
            return ResponseEntity.ok("Please verify your email.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to send email for verification");
        }
    }

    @GetMapping("/registration-confirmation")
    public ResponseEntity<?> registrationConfirmation(@RequestParam(name = "name") String name, @RequestParam(name = "password") String password, @RequestParam(name = "email") String email) {
        if (userService.existByEmail(email)) {
            return ResponseEntity.ok("Email exist");
        }
        userService.registerUser(name, password, email);
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response){
        User user = userService.findByEmail(userDto.getEmail());
        if(user!=null){
            if(userService.comparePassword(userDto.getPassword(),user.getPassword())){
                String token = UUID.randomUUID().toString();
                Cookie cookie = new Cookie("token",token);
                cookie.setPath("/shoe/api/auth/login");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return ResponseEntity.ok("successfully");
            }
            return ResponseEntity.ok(" sai");
        }
        return ResponseEntity.ok("unsuccessfully");
    }

    @PostMapping("/change-password/{id}")
    public ResponseEntity<?> forgotPassword(@RequestBody PasswordChangeRequest passwordChangeRequest, @PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            if (userService.changePassword(user, passwordChangeRequest.getOldPassword(), passwordChangeRequest.getNewPassword())) {
                return ResponseEntity.ok("Change password successfully");
            }
        }
        return ResponseEntity.ok("Change password failed");
    }

}