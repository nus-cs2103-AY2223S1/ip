package duke;

/**
 * Exception thrown when DukeBot does not recognise the command.
 */
public class InvalidTaskException extends DukeException {
    public InvalidTaskException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

}
