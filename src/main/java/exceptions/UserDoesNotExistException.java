package exceptions;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException() {
        super("The requested user does not exist");
    }
}
