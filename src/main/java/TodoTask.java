public class TodoTask extends Task {
    TodoTask(String action, boolean isDone) {
        super(action, isDone);
    }

    TodoTask(String action) {
        this(action, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
