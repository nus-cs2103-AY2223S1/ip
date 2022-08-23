package duke.task;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStorageFormat() {
        return "T | " + super.getStorageFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
