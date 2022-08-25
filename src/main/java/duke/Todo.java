package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStorageString() {
        return "T" + super.toStorageString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
