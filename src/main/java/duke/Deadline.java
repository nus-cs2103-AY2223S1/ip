package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class which encapsulates a task which has a deadline.
 *
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor.
     *
     * @param name
     * @param deadline
     */
    public Deadline(String name, String deadline) throws DateTimeParseException {
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns String representation of Deadline object.
     *
     * @return String representation of Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the deadline of the Deadline object.
     * @return the deadline in String format
     */
    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof ToDo || task instanceof Event) {
            return -1;
        } else {
            Deadline otherDeadline = (Deadline) task;
            return this.deadline.compareTo(otherDeadline.deadline);
        }
    }

    /**
     * Returns deadline String in format YYYY-MM-DD
     *
     * @return the deadline
     */
    public String toLocalDate() {
        return this.deadline.toString();
    }
}
