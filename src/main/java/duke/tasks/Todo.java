package duke.tasks;


public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Shows the todo task description.
     *
     * @return String with the todo task description.
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
