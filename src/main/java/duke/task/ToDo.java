package duke.task;

/**
 * Represents a task to be done in Duke.
 */
public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
