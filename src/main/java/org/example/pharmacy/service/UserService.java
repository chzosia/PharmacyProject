package org.example.pharmacy.service;

import org.example.pharmacy.controller.dto.CreateUserRequestDto;
import org.example.pharmacy.controller.dto.CreateUserResponseDto;
import org.example.pharmacy.controller.dto.UserResponseDto;
import org.example.pharmacy.infrastructure.entity.UserEntity;
import org.example.pharmacy.infrastructure.repository.IUserRepository;
import org.example.pharmacy.service.errors.UserNotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserResponseDto createUser(CreateUserRequestDto userDto) {
        var userEntity = new UserEntity();

        var hashedPassword = passwordEncoder.encode(userDto.getPassword());

        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(hashedPassword);
        var savedUser = userRepository.save(userEntity);

        return new CreateUserResponseDto(savedUser.getId());
    }

    public UserResponseDto getUser(long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDto(user.getId(), user.getUsername());
    }

    public UserResponseDto getUserByUsername(String username) {
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundError(username));
        return new UserResponseDto(user.getId(), user.getUsername());
    }

    // Method for partial update
    public UserResponseDto updateUser(Long id, CreateUserRequestDto userDto) {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundError(id));

        // Only update the fields that are not null in the request
        if (userDto.getUsername() != null) existingUser.setUsername(userDto.getUsername());
        if (userDto.getPassword() != null) existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(existingUser);

        return new UserResponseDto(existingUser.getId(), existingUser.getUsername());

    }

    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundError(id));
        userRepository.delete(user);
    }


}
