package sally.task;

/**
 * ToDo class to represent new Todo task
 *
 * @author liviamil
 */

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getOutput() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
