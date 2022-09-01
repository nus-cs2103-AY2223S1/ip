package duke.exceptions;

/**
 * DukeException implements method for exceptions in Duke.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class DukeException extends Exception {

    /**
     * Creates a new DukeException object.
     *
     * @param message the error message to be displayed
     */
    public DukeException(String message) {
        super("    " + message + "\n");
    }
}
