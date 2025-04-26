package com.login.login.controller;

import lombok.AllArgsConstructor;
import com.login.login.model.dto.LoginUserDTO;
import com.login.login.model.dto.UserRegisterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.login.login.service.AuthService;
import com.login.login.service.RegisterService;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO body) {
        System.out.println("[DEBUG] Recebido registro: " + body.getEmail());
        try {
            if (!body.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
                throw new IllegalArgumentException("Invalid email");
            }

            registerService.registerUser(body.getEmail(), body.getPassword());
            return ResponseEntity.ok("Registered user");
        }
        catch (IllegalArgumentException e) {
            log.warn("Erro ao registrar email de usuário", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginUserDTO body) {
        return authService.authenticateUser(body.getEmail(), body.getPassword());
    }
}
