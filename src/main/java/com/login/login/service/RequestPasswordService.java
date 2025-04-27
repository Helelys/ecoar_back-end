package com.login.login.service;

import com.login.login.model.dto.EmailDTO;
import com.login.login.model.entity.User;
import com.login.login.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RequestPasswordService {

    private final UserRepository userRepository;
    private final SendEmailService sendEmailService;

    public void resetPasswordUser(EmailDTO email) {
        User user = userRepository.findByEmail(email.getEmail()).orElse(null);
        if (user != null) {

            user.setResetToken(null);
            user.setDateTime(null);
            userRepository.save(user);

            String token = UUID.randomUUID().toString().substring(0, 6);

            user.setResetToken(token);
            user.setDateTime(LocalDateTime.now().plusHours(1));
            userRepository.save(user);

            try {
                sendEmailService.sendEmailResetPassword(user.getEmail(), token);
            } catch (Exception e) {
                throw new RuntimeException("Cannot send email");
            }
        }
    }
}
