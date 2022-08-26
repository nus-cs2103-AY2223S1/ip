package duke.tasks;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isMarked, String description) {
        super(isMarked, description);
    }

    @Override
    public String dbRepresentation() {
        return String.join("|", "T", Boolean.toString(isMarked),  description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
