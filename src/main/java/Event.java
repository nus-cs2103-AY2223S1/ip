import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm");
        try {
            this.at = LocalDateTime.parse(at, formatter);
        } catch (Exception e) {
            throw new DukeException("Please enter date and time in the format: dd/M/yyyy HH:mm");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma")) + ")";
    }
}
