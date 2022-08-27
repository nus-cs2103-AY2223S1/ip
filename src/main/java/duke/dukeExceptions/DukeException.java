package duke.dukeExceptions;

/**
 * Represents a DukeException
 * 
 * @author Ramanathan Kumarappan
 */
public class DukeException extends Exception{
    public DukeException(String errorMsg) {
        super("OPS!!!! " + errorMsg);
    }
}
