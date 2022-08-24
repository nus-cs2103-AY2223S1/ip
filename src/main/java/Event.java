import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
