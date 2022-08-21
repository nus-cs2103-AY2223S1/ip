import java.time.LocalDateTime;

public class Events extends Task {
    private LocalDateTime timing;

    public Events(String task, String timing) throws DukeException {
        super(task);
        this.timing = ConvertDateTime(timing);
    }

    public Events (String task, String timing, boolean done) {
        super(task, done);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", timing);
    }

    @Override
    public String toSaveString() {
        return "event " + super.toSaveString() + " " + timing;
    }
}
