package org.example.shoe.sevices;

import org.example.shoe.dto.UserDto;
import org.example.shoe.entity.Role;
import org.example.shoe.entity.User;
import org.example.shoe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean existByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            return true;
        }
        return false;
    }

    public void registerUser(String name, String password, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setVerification_token(null);
        user.setRole(new Role(2L));
        userRepository.save(user);
    }

    public boolean login(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            return passwordEncoder.matches(user.getPassword(), userDto.getPassword());
        }
        return false;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean comparePassword(String rawPassword,String encodedPassword){
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }
    public boolean changePassword(Optional<User> optionalUser, String oldPassword, String newPassword) {
        User user1 = optionalUser.get();

        if (passwordEncoder.matches(oldPassword,user1.getPassword())) {
            user1.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user1);
            return true;
        }
        return false;
    }


}
