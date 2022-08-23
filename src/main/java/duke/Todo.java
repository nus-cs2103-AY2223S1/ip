package duke;

public class Todo extends Task {

    protected String at;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStringFileFormat() {
        return "T | " + super.toStringFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}