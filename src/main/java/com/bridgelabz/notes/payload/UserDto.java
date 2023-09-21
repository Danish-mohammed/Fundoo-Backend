package com.bridgelabz.notes.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String fname;
    private String lname;
    private String email;
    private String password;
}
