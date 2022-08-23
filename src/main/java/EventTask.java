import java.util.Objects;

public class EventTask extends Task {

    EventTask(String action, boolean isDone, String event) throws DukeException {
        super(action, isDone, event);
        if (Objects.equals(event, "")) {
            throw new DukeException();
        }
    }

    EventTask(String action, String event) throws DukeException {
        this(action, false, event);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
