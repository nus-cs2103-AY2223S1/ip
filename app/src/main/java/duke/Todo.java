package duke;

public class Todo extends Task {
    /**
     * Creates a new Todo.
     * @param details What needs to be done.
     */
    public Todo(String details) {
        super(details);
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }
}
