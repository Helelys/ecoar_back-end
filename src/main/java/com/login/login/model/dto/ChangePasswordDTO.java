package com.login.login.model.dto;

import lombok.Getter;

@Getter
public class ChangePasswordDTO {

    private String resetToken;
    private String password;
}
