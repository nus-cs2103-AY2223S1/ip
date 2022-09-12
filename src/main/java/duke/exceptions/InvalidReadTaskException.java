package duke.exceptions;

/**
 * Thrown when an invalid number/type of arguments are read from save file
 */

public class InvalidReadTaskException extends Exception {

    public InvalidReadTaskException(String message) {
        super(message);
    }
}
