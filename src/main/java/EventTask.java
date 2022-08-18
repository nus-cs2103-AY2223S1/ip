import java.util.Objects;

public class EventTask extends Task {
    protected String event;

    EventTask(String action, boolean isDone, String event) throws DukeException {
        super(action, isDone);
        if (Objects.equals(event, "")) {
            throw new DukeException();
        }
        this.event = event;
    }

    EventTask(String action, String event) throws DukeException {
        this(action, false, event);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + event + ")";
    }
}
