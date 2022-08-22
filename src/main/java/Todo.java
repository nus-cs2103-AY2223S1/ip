public class Todo extends Task {
    Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    Todo(String description) {
        super(description);
    }

    @Override
    String getStorageFormat() {
        return "T | " + super.getStorageFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
