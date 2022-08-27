package duke;

public class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "T | " + this.getStatusIcon() + " | " + this.getDescription();
    }
}
