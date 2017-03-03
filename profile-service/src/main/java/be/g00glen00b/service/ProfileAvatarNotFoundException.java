package be.g00glen00b.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProfileAvatarNotFoundException extends RuntimeException {
    public ProfileAvatarNotFoundException() {
    }

    public ProfileAvatarNotFoundException(String message) {
        super(message);
    }
}
