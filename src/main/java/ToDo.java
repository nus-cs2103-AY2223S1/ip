public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String deadline, boolean isDone) {
        super(deadline, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        return "T , " + super.toFileString();
    }
}
