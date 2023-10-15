package exceptions;

public class UserIsAlreadyExistsException extends RuntimeException {
    public UserIsAlreadyExistsException(String message) {
        super(message);
    }
}
