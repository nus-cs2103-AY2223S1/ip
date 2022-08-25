package duke.task;

import duke.DukeException;

public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Constructor for task in save file.
     * @param description Description of the duke.task.Todo.
     * @param done If the duke.task.Todo task is done or not done.
     */
    public Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}