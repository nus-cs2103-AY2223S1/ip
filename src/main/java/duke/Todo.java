package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the details of the todo.
     *
     * @return Details of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
