package org.example.pharmacy.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundError extends RuntimeException {
    public UserNotFoundError(String username) {
        super("User with username " + username + " not found");
    }

    public UserNotFoundError(Long id) {
        super("User with ID " + id + " not found");
    }
}
