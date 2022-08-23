public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTask() {
        String done = this.isDone ? "1" : "0";
        return String.format("T | %s | %s", done, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
