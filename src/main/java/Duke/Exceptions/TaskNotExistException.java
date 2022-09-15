package duke.exceptions;

/**
 * Class that denotes the Exception for non-existence of task input.
 */
public class TaskNotExistException extends DukeException {
    @Override
    public String toString() {
        return "Sorry, I don't understand your words\n : (";
    }
}
