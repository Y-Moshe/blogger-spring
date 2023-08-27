package com.blogger.bloggerspring.Dtos;

import lombok.Data;

import java.util.Date;

import lombok.Builder;

@Data
@Builder
public class AuthResponseDto {

    private UserDto user;
    private String token;
    private Date expirationDate;
}
