package com.mintidea.thikadar.module.auth.dto;

import com.mintidea.thikadar.module.auth.model.User;
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

    public AuthenticationResponse(boolean isPresent){
        this.isPresent=isPresent;
    }

}
