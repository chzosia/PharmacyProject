package org.example.pharmacy.service;

import org.example.pharmacy.controller.dto.LoginRequestDto;
import org.example.pharmacy.controller.dto.LoginResponseDto;
import org.example.pharmacy.infrastructure.repository.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(IUserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        var user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        var doPasswordMatch = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());

        if(!doPasswordMatch) {
            throw new RuntimeException("Unauthorized");
        }

        return new LoginResponseDto(jwtService.createToken(user));
    }
}
