package bobby.task;

public class Todo extends Task {
    /**
     * Constructor for Todo task
     * @param description task description
     * @param isDone task status
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);

    }

    /**
     * Constructor for Todo task
     * @param description task description
     */
    public Todo(String description) {
        super(description);
    }
    @Override
    public String formatTaskString() {
        return String.format("T|%s|%s", this.description, this.isDone);
    }

    @Override
    public String toString() {
        return String.format("[T] [%s] %s", this.getStatusIcon(), this.description);
    }

}
