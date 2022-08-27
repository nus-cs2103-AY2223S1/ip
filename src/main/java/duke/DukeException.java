package duke;
/**
 * Represents an exception thrown when an error occurs during duke chatbot execution.
 */
public class DukeException extends Exception{
    private String msg;
    public DukeException(String msg) {
        super(msg);
    }
}
