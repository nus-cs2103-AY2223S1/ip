package exceptions;

/**
 * Custom Exception class for Duke program.
 */
public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super("DUKEERROR: " + errorMessage);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}

