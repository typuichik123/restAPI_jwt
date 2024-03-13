package com.dmitrij.restapi_jwt.DTOs;

import lombok.Data;

@Data
public class RegistrationUserDTO {

    private int id;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String role;

}
