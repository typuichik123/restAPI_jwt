package com.dmitrij.restapi_jwt.DTOs;

import lombok.Data;

@Data
public class JwtRequest {
    private String userName;
    private String password;
}
