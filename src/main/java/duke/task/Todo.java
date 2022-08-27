package duke.task;

public class Todo extends Task {
    /**
     * Constructs a to-do task.
     *
     * @param description Description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Shows the to-do task description.
     *
     * @return String with the to-do task description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveTask() {
        return String.format("T | %s", super.saveTask());
    }
}