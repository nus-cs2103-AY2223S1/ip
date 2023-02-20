package exceptions;

/**
 * UserDoesNotExistException is thrown if the user does not exist
 */
public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException() {
        super("The requested user does not exist");
    }
}
