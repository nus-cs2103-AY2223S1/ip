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

    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        this.at = at;
        try {
            this.event = LocalDateTime.parse(at.substring(1));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the deadline in yyyy-mm-ddTHours:Minutes:Seconds format. " +
                    "E.g 2019-10-15T10:15:00");
        }
    }

    private String printTime() {
        String s = this.event.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm:ss a"));
        return s;
    }

    @Override
    public String fileStatus() {
        return "E | " + super.fileStatus() + "|" + this.at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + printTime() + ")";
    }
}

