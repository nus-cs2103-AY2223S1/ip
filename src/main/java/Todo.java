public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int status) {
        super(description, status);
    }

    @Override
    public String parseToSaveData() {
        return "T" + "|" + super.parseToSaveData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
