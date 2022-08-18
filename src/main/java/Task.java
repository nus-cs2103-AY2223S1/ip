import java.util.Objects;

public class Task {
    protected String action;
    protected boolean isDone;

    Task(String action, boolean isDone) throws DukeException {
        if (Objects.equals(action, "")) {
            throw new DukeException();
        }
        this.action = action;
        this.isDone = isDone;
    }

    Task(String action) throws DukeException {
        this(action, false);
    }

    String getAction() {
        return action;
    }

    void markDone() {
        isDone = true;
    }

    void markUnDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return ("[" + (isDone ? "X" : " ") + "] " + action);
    }
}
