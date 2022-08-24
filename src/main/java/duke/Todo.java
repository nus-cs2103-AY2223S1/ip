package duke;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, "T", isDone);
    }

    @Override
    public String getCsvString() {
        return String.format("todo %s", super.getCsvString());
    }
}
