import java.time.LocalDateTime;

public class Events extends Task {
    private LocalDateTime timing;

    public Events(String task, String timing) throws DukeException {
        super(task);
        this.timing = ConvertDateTime(timing);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", timing);
    }
}
