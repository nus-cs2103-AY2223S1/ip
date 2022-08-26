package duke.models;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description,isDone);
    }

    public String formatForSave() {
        return "T | " + super.formatForSave();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
