package be.g00glen00b.service.data;

public class ProfileAlreadyExistsException extends RuntimeException {
    public ProfileAlreadyExistsException() {
    }

    public ProfileAlreadyExistsException(String message) {
        super(message);
    }
}
