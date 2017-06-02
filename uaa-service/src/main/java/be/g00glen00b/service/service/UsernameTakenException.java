package be.g00glen00b.service.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException() {
    }

    public UsernameTakenException(String message) {
        super(message);
    }
}
