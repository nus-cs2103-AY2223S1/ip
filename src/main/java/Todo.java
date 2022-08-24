public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String encode() {
        return "T | " + super.encode();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
