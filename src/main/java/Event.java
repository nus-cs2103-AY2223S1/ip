import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDateTime event;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        try {
            this.event = LocalDateTime.parse(at.substring(1));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the deadline in yyyy-mm-ddTHours:Minutes:Seconds format. " +
                    "E.g 2019-10-15T10:15:00");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + this.event.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm:ss a")) + ")";
    }
}
