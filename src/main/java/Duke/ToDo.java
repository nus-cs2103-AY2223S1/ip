package duke;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toWrite() {
        return String.format("T~%s~%s", (isDone ? "1" : "0"), description.trim());
    }
}
