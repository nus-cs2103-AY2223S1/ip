package duke;

/**
 * DukeException is custom Exception to be thrown
 * when user inputs invalid commands
 */
public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
