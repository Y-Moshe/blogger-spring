package com.blogger.bloggerspring.Services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogger.bloggerspring.Entities.User;
import com.blogger.bloggerspring.Interfaces.IUserService;
import com.blogger.bloggerspring.Repositories.UserRepository;

@Service
public class UserService implements IUserService {
    private UserRepository _repository;
    private BCryptPasswordEncoder _bcrypt;

    public UserService(UserRepository repository) {
        _repository = repository;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return _repository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return _repository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        String hashedPassword = _bcrypt.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return _repository.save(user);
    }
}
