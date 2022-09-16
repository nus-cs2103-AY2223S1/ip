package hazell.exceptions;

/**
 * Exception when the user specified an empty description when creating a Task.
 */
public class TaskDescriptionEmpty extends HazellException {
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
