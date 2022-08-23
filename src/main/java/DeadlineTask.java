import java.util.Objects;

public class DeadlineTask extends Task {

    DeadlineTask(String action, boolean isDone, String by) throws DukeException {
        super(action, isDone, by);
        if (Objects.equals(by, "")) {
            throw new DukeException();
        }
    }

    DeadlineTask(String action, String by) throws DukeException {
        this(action, false, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
