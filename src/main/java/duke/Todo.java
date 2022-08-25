package duke;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, "T", isDone);
    }

    @Override
    public String getTxtString() {
        return String.format("todo %s", super.getTxtString());
    }
}
