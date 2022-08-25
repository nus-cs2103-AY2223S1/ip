package DukeBot;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Encapsulates the Deadline sub-class of Task class.
 */
public class Deadline extends Task {

    private LocalDate time;

    public Deadline(String description, String time) throws DukeException {
        super(description);
        try {
            this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTime not in yyyy-MM-dd format");
        }
    }

    /**
     * Get the type of Task.
     *
     * @return "D" indicating Deadline.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Get the time of the Deadline.
     *
     * @return by The time of the Deadline.
     */
    @Override
    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Get string representation of Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")", this.getTaskType(), super.toString());
    }
}
