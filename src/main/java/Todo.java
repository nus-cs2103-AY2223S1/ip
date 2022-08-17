public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getTaskIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getTaskIcon(), super.toString());
    }
}
