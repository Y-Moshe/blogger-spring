package com.blogger.bloggerspring.Controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.bloggerspring.Entities.User;
import com.blogger.bloggerspring.Errors.ApiError;
import com.blogger.bloggerspring.Services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name = "Account Controller")
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final UserService _userService;

    @GetMapping()
    public ResponseEntity<User> getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>((User) auth.getDetails(), HttpStatus.OK);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkUser(@RequestParam String email) {
        Optional<User> user = _userService.getUserByEmail(email);
        return new ResponseEntity<>(user != null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = _userService.getUserById(id);

        ApiError error = new ApiError("User not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user.orElseThrow(() -> error), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(_userService.saveUser(user), HttpStatus.CREATED);
    }
}
