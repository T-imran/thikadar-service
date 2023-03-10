package com.example.task.auth;

import com.example.task.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private User user;
    private String token;
    private boolean isPresent;

    AuthenticationResponse(boolean isPresent){
        this.isPresent=isPresent;
    }

}
