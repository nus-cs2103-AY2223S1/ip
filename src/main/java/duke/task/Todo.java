package duke.task;

import java.time.LocalDate;

import duke.DukeException;

/**
 * The Todo Task.
 * Inherits from Task.
 */
public class Todo extends Task {

    /**
     * Creates the Todo Task.
     *
     * @param description The Todo at hand.
     * @throws DukeException If the Todo description is empty.
     */
    public Todo(String description) throws DukeException {
        super(description);
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Creates the Todo Task.
     *
     * @param description The Todo at hand.
     * @param isDone Whether the Todo is completed.
     * @throws DukeException If the Todo description is empty.
     */
    public Todo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Updates the date field of certain Tasks.
     *
     * @param date The date to be changed to.
     * @throws DukeException Throws DukeException if executed on Todo Task.
     */
    @Override
    public void updateTaskDate(LocalDate date) throws DukeException {
        throw new DukeException("You cannot change the date of a Todo!");
    }

    /**
     * Returns the String representation of Todo.
     *
     * @return The String representation of Todo showing the status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
