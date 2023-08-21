package com.blogger.bloggerspring.Entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
public class User extends Auditable {

    @NotBlank
    @NonNull
    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @NotBlank
    @NonNull
    @Column(nullable = false)
    @Length(min = 6)
    private String password;
}
