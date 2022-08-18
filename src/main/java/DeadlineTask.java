import java.util.Objects;

public class DeadlineTask extends Task {
    protected String by;

    DeadlineTask(String action, boolean isDone, String by) throws DukeException {
        super(action, isDone);
        if (Objects.equals(by, "")) {
            throw new DukeException();
        }
        this.by = by;
    }

    DeadlineTask(String action, String by) throws DukeException {
        this(action, false, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
