public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toStringData() {
        return "T | " + super.toStringData();
    }
}