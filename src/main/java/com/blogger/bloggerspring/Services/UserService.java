package com.blogger.bloggerspring.Services;

import java.util.Optional;

import com.blogger.bloggerspring.Dtos.AuthCredentialsDto;
import com.blogger.bloggerspring.Dtos.AuthResponseDto;
import com.blogger.bloggerspring.Entities.User;

public interface UserService {
    User getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    AuthResponseDto createUser(User User);

    AuthResponseDto authenticateUser(AuthCredentialsDto credentials);

    User getLoggedInUser();
}
