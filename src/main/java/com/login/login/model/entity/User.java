package com.login.login.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "user")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uid_user")
    private String id;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
