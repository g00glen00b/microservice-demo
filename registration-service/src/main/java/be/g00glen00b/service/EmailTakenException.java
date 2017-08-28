package be.g00glen00b.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailTakenException extends RuntimeException {
    public EmailTakenException() {
    }

    public EmailTakenException(String message) {
        super(message);
    }
}
