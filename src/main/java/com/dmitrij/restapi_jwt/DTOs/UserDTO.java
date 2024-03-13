package com.dmitrij.restapi_jwt.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserDTO {
    private String userName;
    private String password;

}
