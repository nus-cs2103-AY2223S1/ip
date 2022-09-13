package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.ImproperDeadlineFormatException;

/**
 * Represents a deadline task.
 * A <code>Deadline</code> object corresponds to
 * a description of a task and a deadline
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("h:mm a");

    private LocalTime time;
    private LocalDate date;
    private String by;

    /**
     * Deadline consist of description and deadline.
     *
     * @param description description of task.
     * @param by deadline of task.
     * @throws ImproperDeadlineFormatException if deadline does not follow the format of
     *                                         deadline (description) /by YYYY-MM-DD hh:mm
     */
    public Deadline(String description, String by) throws ImproperDeadlineFormatException {
        super(description);
        this.by = by; // by: " YYYY-MM-DD hh:mm"
        try {
            String[] arr = by.split(" "); // arr: ["", "YYYY-MM-DD", "hh:mm"]
            this.date = LocalDate.parse(arr[1]);
            this.time = LocalTime.parse(arr[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ImproperDeadlineFormatException();
        } catch (DateTimeParseException e) {
            throw new ImproperDeadlineFormatException();
        }
    }

    /**
     * Returns String representation of deadline
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[D] "
                + this.getStatusIcon()
                + " " + super.description
                + " (by: "
                + date.format(DATE_FORMAT)
                + ", "
                + time.format(TIME_FORMAT)
                + ")";
    }

    /**
     * Returns String representation in
     * "D|0 or 1|task|by|"
     * where 1 represents the task is marked and 0 otherwise
     *
     * @return String representation.
     */
    @Override
    public String toSaveVersion() {
        if (this.isDone()) {
            return "D|1|"
                    + super.description
                    + "|"
                    + this.by
                    + "\n";
        }
        return "D|0|"
                + super.description
                + "|"
                + this.by
                + "\n";
    }

    /**
     * Returns true if two deadlines share the same
     * description and deadline.
     *
     * @param obj target Object of comparison
     * @return boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline x = (Deadline) obj;
            if (this.description == null
                    || this.by == null
                    || x.description == null
                    || x.by == null) {
                return false;
            }
            return this.description.equals(x.description)
                    && this.by.equals(x.by);
        }

        return false;
    }

    /**
     * Returns date and time representation.
     *
     * @return LocalDateTime.
     */
    private LocalDateTime getDateTime() {
        return LocalDateTime.of(this.date, this.time);
    }

    /**
     * Returns 1 if this deadline's deadline is larger than
     * target's deadline.
     * 0 if both deadlines are the same.
     * -1 if deadline is smaller than target's deadline.
     *
     * @param deadline target deadline of comparison.
     * @return int.
     */
    public int compareChronologically(Deadline deadline) {
        return this.getDateTime().compareTo(deadline.getDateTime());
    }


    /**
     * Returns 1 if this deadline's description's is larger than
     * target's description.
     * 0 if both description are the same.
     * -1 if description is smaller than target's description.
     *
     * @param deadline target deadline of comparison.
     * @return int.
     */
    public int compareLexicographically(Deadline deadline) {
        return this.description.compareTo(deadline.description);
    }
}
