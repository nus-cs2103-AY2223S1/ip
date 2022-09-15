package duke;

/**
 * DukeException is custom Exception to be thrown
 * when user inputs invalid commands
 */
public class DukeException extends Exception{
    /**
     * Constructor for DukeException class
     * @param errorMessage message string to be shown to user
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
