package com.bridgelabz.notes.entities;

import com.bridgelabz.notes.payload.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fname;
    private String lname;
    private String email;
    private String password;

    public User(UserDto userDto) {
        this.fname = userDto.getFname();
        this.lname = userDto.getLname();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
    }
}
