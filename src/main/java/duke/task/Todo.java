package duke.task;

import duke.DukeCommand;

/**
 * A Task which is a Todo.
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo.
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public DukeCommand getTaskType() {
        return DukeCommand.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
