package duke.task;

import java.time.LocalDate;

/**
 * Todo is a basic Task, only containing description.
 */
public class Todo extends Task {

    private static final LocalDate DATE = LocalDate.MIN;
    /**
     * Initializes a Todo object.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description, DATE.toString());
    }

    /**
     * Returns a string representation of a todo.
     *
     * @return Details regarding this todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
