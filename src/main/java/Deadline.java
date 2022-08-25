import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
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

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String fileStatus() {
        return "D | " + super.fileStatus() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm:ss a")) + ")";
    }
}
