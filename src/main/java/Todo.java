public class Todo extends Task {
    Todo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    Todo(String description) {
        this(description, false);
    }

    @Override
    String toStorageFormat() {
        return "T | " + super.toStorageFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
