package com.blogger.bloggerspring.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterDto {

    @NotBlank
    @Length(min = 3, max = 500)
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Length(min = 6, max = 24)
    private String password;
}