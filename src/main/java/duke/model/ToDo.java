package duke.model;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toStorage() {
        return "T | " + (this.isDone ? 1 : 0) + " | " + description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
