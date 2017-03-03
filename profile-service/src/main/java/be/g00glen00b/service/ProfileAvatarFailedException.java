package be.g00glen00b.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProfileAvatarFailedException extends RuntimeException {
    public ProfileAvatarFailedException() {
    }

    public ProfileAvatarFailedException(String message) {
        super(message);
    }

    public ProfileAvatarFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
