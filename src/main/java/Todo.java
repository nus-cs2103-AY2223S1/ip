public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toDbString() {
        return "T" + " | " + super.toDbString();
    }
}
