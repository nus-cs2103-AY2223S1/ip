import java.util.Objects;

public class Task {
    protected String action;
    protected boolean isDone;
    protected String date;

    Task(String action, boolean isDone, String date) throws DukeException {
        if (Objects.equals(action, "")) {
            throw new DukeException();
        }
        this.action = action;
        this.isDone = isDone;
        this.date = date;
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
