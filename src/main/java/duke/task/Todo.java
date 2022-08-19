package duke.task;

public class Todo extends Task {
    public Todo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    public Todo(String description) {
        this(description, false);
    }

    @Override
    public String toStorageFormat() {
        return "T | " + super.toStorageFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
