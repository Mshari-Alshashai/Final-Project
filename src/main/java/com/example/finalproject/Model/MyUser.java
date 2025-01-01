package com.example.finalproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Empty username")
    private String username;
    @Column(nullable = false)
    @NotEmpty(message = "Empty password")
    @Size(min = 8,message = "Password should be at least 8 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*^?&])[A-Za-z\\d@$!%*^?&]{8,}$", message = "Password must be at least 8 characters long and contain one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;
    @Column(nullable = false)
    @NotEmpty(message = "Empty name")
    private String name;
    @Email(message = "Enter valid email")
    @NotEmpty(message = "Empty email")
    @Column(nullable = false, unique = true)
    private String email;
    @Column(columnDefinition = "varchar(10) not null unique")
    @NotEmpty(message = "Phone number not valid")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and be 10 digits")
    private String phoneNumber;
    @Column
    @Pattern(regexp = "PLAYER|DEVELOPER|REVIEWER|ADMIN",message = "Role must be PLAYER, DEVELOPER, REVIEWER, or ADMIN")
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Player player;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Developer developer;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Reviewer reviewer;


}
