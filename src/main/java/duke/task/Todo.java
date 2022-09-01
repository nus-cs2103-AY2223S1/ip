package duke.task;

/**
 * A Todo is a Task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param isDone boolean denoting whether Todo should be marked.
     * @param text description of task.
     */
    public Todo(boolean isDone, String text) {
        super(isDone, text);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
