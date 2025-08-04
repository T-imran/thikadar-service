package com.mintidea.thikadar.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public class UserExceptions extends RuntimeException{

    private HttpStatus status;
    private String message;

}
