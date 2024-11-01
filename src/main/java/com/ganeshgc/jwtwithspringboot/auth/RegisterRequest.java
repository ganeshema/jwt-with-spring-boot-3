package com.ganeshgc.jwtwithspringboot.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
