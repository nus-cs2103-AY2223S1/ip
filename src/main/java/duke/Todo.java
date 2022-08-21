package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int status) {
        this(description);
        isDone = status == 1;
    }

    @Override
    public String parseToSaveData() {
        return "T" + "|" + super.parseToSaveData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
