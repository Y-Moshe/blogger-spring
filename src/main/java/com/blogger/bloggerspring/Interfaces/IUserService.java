package com.blogger.bloggerspring.Interfaces;

import java.util.Optional;

import com.blogger.bloggerspring.Entities.User;

public interface IUserService {
    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    User saveUser(User User);
}
