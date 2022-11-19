package duke;

/**
 * Custom exception for exceptions thrown by duke program
 *
 * @author Sean Lam
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
