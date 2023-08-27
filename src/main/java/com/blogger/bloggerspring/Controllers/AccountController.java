package com.blogger.bloggerspring.Controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.bloggerspring.Dtos.AuthCredentialsDto;
import com.blogger.bloggerspring.Dtos.AuthRegisterDto;
import com.blogger.bloggerspring.Dtos.AuthResponseDto;
import com.blogger.bloggerspring.Dtos.UserDto;
import com.blogger.bloggerspring.Entities.User;
import com.blogger.bloggerspring.Mappers.UserMapper;
import com.blogger.bloggerspring.Services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name = "Account Controller")
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final UserService _userService;

    @GetMapping()
    public ResponseEntity<UserDto> getLoggedUser() {
        User user = _userService.getLoggedInUser();
        return new ResponseEntity<UserDto>(
                UserMapper.MAPPER.mapToUserDto(user),
                HttpStatus.OK);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkUser(@RequestParam String email) {
        Optional<User> user = _userService.getUserByEmail(email);
        return new ResponseEntity<>(
                user.isPresent(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(
                UserMapper.MAPPER.mapToUserDto(_userService.getUserById(id)),
                HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> createUser(
            @Valid @RequestBody AuthRegisterDto registerDto) {
        return new ResponseEntity<>(
                _userService.createUser(UserMapper.MAPPER.mapToUser(registerDto)),
                HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDto> authenticateUser(
            @Valid @RequestBody AuthCredentialsDto credentials) {
        return new ResponseEntity<>(
                _userService.authenticateUser(credentials),
                HttpStatus.OK);
    }
}
