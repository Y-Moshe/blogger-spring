package com.blogger.bloggerspring.Dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCredentialsDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 6, max = 24)
    private String password;
}
