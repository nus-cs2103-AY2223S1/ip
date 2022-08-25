import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by; // "Aug 26 2022 07:00:00 PM" i want to format it back to yyyy-mm-ddTHours:Minute:Seconds
    protected LocalDateTime deadline;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            this.deadline = LocalDateTime.parse(by.substring(1));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the deadline in yyyy-mm-ddTHours:Minute:Seconds format. " +
                    "E.g 2019-10-15T10:15:00");
        }
    }

    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        this.by = by;
        try {
            this.deadline = LocalDateTime.parse(by.substring(1));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the deadline in yyyy-mm-ddTHours:Minute:Seconds format. " +
                    "E.g 2019-10-15T10:15:00");
        }
    }

    private String printTime() {
        String s = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm:ss a"));
        return s;
    }
    @Override
    public String fileStatus() {
        return "D | " + super.fileStatus() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + printTime() + ")";
    }
}
