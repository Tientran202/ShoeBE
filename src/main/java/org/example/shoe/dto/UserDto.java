package org.example.shoe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotBlank(message = "Username can not left blank")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "The username must include uppercase letters, lowercase letters and numbers")
    @Size(min = 4, max = 12, message = "The username must be between 4 to 12 characters long")
    private String name;

    @NotBlank(message = "Email can not left blank")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@gmail\\.com$", message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password can not left blank")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$", message = "Password must include numbers, uppercase letters, lowercase letters and special characters including  '!@#$%^&+='")
    @Size(min = 4, max = 20, message = "Nhập mật khẩu từ 4-20 kí tự")
    private String password;

    public UserDto() {
    }

    public UserDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
