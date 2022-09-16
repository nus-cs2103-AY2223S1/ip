package duke.task;

import duke.DukeException;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructor method for a Todo.
     *
     * @param description description of the task
     */
    public Todo(String description) throws DukeException {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo to be saved.
     *
     * @return string representation of the Todo to be saved
     */
    @Override
    public String save() {
        return "T" + super.save();
    }
}
