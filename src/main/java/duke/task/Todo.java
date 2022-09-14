package duke.task;

import duke.utilities.DukeException;

/**
 * Encapsulates a to-do task.
 */
public class Todo extends Task{

    /**
     * Constructs a new {@code Todo} with given description.
     *
     * @param description The description of the task.
     * @throws DukeException Handles when description is empty.
     */
    public Todo(String description) throws DukeException {
        super(description);
        if (description.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
