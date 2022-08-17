public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + TaskType.T + "]" + super.toString();
    }
}
