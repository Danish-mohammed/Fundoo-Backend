package com.bridgelabz.notes.entities;

import com.bridgelabz.notes.payload.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @NotNull
    private String fname;

    @NotNull
    private String lname;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String profile;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate regDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate updateDate;

    @Column(name = "u_active", columnDefinition = "boolean default false")
    private boolean isActive;

    public User(UserDto userDto) {
        this.fname = userDto.getFname();
        this.lname = userDto.getLname();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
    }
}
