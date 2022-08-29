package duke.task;

import java.time.LocalDate;

/**
 * Represents a task that is to be done.
 */
public class ToDo extends Task {

    /**
     * Creates a todo object upon receiving a todo command by the user.
     *
     * @param description The description of the todo.
     * @param type The type of task created.
     */
    public ToDo(String description, TaskType type) {
        super(description, type);
    }

    @Override
    public boolean isDateEqual(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
