package com.ganeshgc.jwtwithspringboot.auth;

import com.ganeshgc.jwtwithspringboot.config.JwtAuthService;
import com.ganeshgc.jwtwithspringboot.role.Role;
import com.ganeshgc.jwtwithspringboot.user.User;
import com.ganeshgc.jwtwithspringboot.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthService jwtAuthService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(Role.USER)
                .build();
        userRepository.save(user);
        var jwt=jwtAuthService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();

    }

    public AuthenticationResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        var  user=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        var jwt=jwtAuthService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }
}
