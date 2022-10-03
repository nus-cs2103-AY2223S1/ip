package duke.task;

import duke.exception.DukeException;

/**
 * Represents a task of type ToDo.
 */
public class ToDo extends Task {

    /**
     * Constructs the ToDo task.
     */
    public ToDo(String description) throws DukeException {
        super(description);
        if (description.isBlank()) {
            throw new DukeException("Take me seriouslyy :( What do you want to do?\n");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
