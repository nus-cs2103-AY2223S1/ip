/**
 * The Todo class is a subclass of Task.
 */
public class Todo extends Task {
    public Todo(String taskName) throws IndexOutOfBoundsException {
        super(taskName.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
