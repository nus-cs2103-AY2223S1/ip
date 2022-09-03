package duke.task;

/**
 * Task of type todo.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getOutput() {
        return String.format("T | %d | %s", getIsDone() ? 1 : 0, getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
