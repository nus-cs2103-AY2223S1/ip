package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.utils.DateTime;

/**
 * Describes the deadline class.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter formatter = DateTime.FORMATTER;
    private final LocalDateTime time;

    /**
     * Constructor when taking user input.
     * @param description description of the deadline.
     * @param time the time of the deadline.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, formatter);
    }

    /**
     * Constructor when reading from database.
     * @param isMarked boolean value of whether the task is marked.
     * @param description description of the deadline.
     * @param time the time of the deadline.
     */
    public Deadline(boolean isMarked, String description, String time) {
        super(isMarked, description);
        System.out.println(time);
        this.time = LocalDateTime.parse(time);
    }

    @Override
    public String dbRepresentation() {
        return String.join("|", "D", Boolean.toString(isMarked), description, time.toString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.time.format(formatter) + ")";
    }
}
