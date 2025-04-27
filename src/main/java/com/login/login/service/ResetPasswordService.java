package com.login.login.service;

import com.login.login.model.entity.User;
import com.login.login.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ResetPasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void resetPassword(String resetToken, String password) {
        User user = userRepository.findByResetToken(resetToken).orElse(null);

        if (user != null && user.getDateTime().isAfter(LocalDateTime.now())) {
            user.setPassword(passwordEncoder.encode(password));
            user.setResetToken(null);
            user.setDateTime(null);
            userRepository.save(user);
        }
    }
}
