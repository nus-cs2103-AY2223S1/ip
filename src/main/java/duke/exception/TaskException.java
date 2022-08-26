package duke.exception;

/**
 * Represents an error during parsing of input from the user.
 * This error will occur when the user attempts to create a task without any description.
 */
public class TaskException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + "description cannot be empty please fill in something";
    }
}
