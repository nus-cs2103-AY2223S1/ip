public class ToDo extends Task {
    ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    ToDo(String description) {
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
