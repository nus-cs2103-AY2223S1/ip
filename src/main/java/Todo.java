public class Todo extends Task {
    public Todo(boolean isDone, String text) {
        super(isDone, text);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
