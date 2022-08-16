public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    public String getTaskIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + super.toString();
    }
}
