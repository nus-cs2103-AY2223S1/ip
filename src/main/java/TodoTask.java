public class TodoTask extends Task {
    TodoTask(String action, boolean isDone) throws DukeException {
        super(action, isDone);
    }

    TodoTask(String action) throws DukeException {
        this(action, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
