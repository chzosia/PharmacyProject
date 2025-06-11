package org.example.pharmacy.service;

import org.example.pharmacy.controller.dto.LoginRequestDto;
import org.example.pharmacy.controller.dto.LoginResponseDto;
import org.example.pharmacy.infrastructure.repository.IUserRepository;
import org.example.pharmacy.service.errors.InvalidCredentialsException;
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
        var userOptional = userRepository.findByUsername(loginRequestDto.getUsername());

        if (userOptional.isEmpty()) {
            throw new InvalidCredentialsException("User does not exist");
        }

        var user = userOptional.get();

        var doPasswordMatch = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());

        if (!doPasswordMatch) {
            throw new InvalidCredentialsException("Incorrect password");
        }

        return new LoginResponseDto(jwtService.createToken(user));
    }

}
