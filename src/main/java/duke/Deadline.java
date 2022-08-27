package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class encapsulates a Deadline task.
 */
public class Deadline extends Task {
    /** A specific date/time for the task to be done */
    protected String by;
    /** The deadline to be stored in the Deadline object */
    protected LocalDateTime deadline;

    /**
     * Instantiates a Deadline object.
     *
     * @param description Description of the task.
     * @param by A specific date/time for the task to be done.
     * @throws DukeException If by is in an invalid format.
     */
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

    /**
     * Instantiates a Deadline object.
     *
     * @param description Description of the task.
     * @param isDone Flag to indicate if the task is done or not.
     * @param by A specific date/time for the task to be done.
     * @throws DukeException If by is in an invalid format.
     */
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

    /**
     * Returns the deadline date/time in a valid format.
     *
     * @return Deadline date/time in a valid format.
     */
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
