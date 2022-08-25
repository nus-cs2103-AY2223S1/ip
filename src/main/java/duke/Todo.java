package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getStorageString() {
        return "T" + "|" + (isDone ? "1" : "0") + "|" + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}