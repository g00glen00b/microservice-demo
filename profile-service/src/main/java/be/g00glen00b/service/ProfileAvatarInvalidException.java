package be.g00glen00b.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProfileAvatarInvalidException extends RuntimeException {
    public ProfileAvatarInvalidException() {
    }

    public ProfileAvatarInvalidException(String message) {
        super(message);
    }
}
