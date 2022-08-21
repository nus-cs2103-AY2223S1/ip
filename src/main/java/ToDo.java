/**
 * Todo represents a Task without any datetime objects associated with it.
 * Inherits from Task.
 */
public class ToDo extends Task {
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
