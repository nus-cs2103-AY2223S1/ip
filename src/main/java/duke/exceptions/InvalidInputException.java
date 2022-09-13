package duke.exceptions;

/** Represents an exception for giving invalid input.*/
public class InvalidInputException extends Exception {

    public InvalidInputException() {
        super("Invalid input!");
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
