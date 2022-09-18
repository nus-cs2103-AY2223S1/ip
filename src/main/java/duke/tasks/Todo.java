package duke.tasks;

public class Todo extends Task {
    /**
     * Creates Todo object.
     * @param description the task description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
