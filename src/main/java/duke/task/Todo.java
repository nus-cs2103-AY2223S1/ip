package duke.task;

import duke.DukeException;

/**
 * Todo is a type of Task that doesn't has a date to it.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description Description of the Todo.
     */
    public Todo(String description) throws DukeException {
        super(description);
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Constructor for task in save file.
     *
     * @param description Description of the Todo.
     * @param done If the Todo task is done or not done.
     */
    public Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}