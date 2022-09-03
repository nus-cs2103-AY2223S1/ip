package duke.task;

/**
 * Represents an Todo Task that can be described and marked as done.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveText() {
        return String.format("%d todo %s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}