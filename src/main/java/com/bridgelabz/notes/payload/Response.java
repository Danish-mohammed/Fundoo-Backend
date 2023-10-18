package com.bridgelabz.notes.payload;

import com.bridgelabz.notes.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private int statusCode;
    private String message;
    private Object data;
}
