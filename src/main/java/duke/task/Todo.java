package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStorage() {
        return "T | " + super.toStorage();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
