package com.login.login.service;

import lombok.AllArgsConstructor;
import com.login.login.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.login.login.repository.UserRepository;

@Service
@AllArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(String email, String password) {
        User user = new User(null, email, passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
