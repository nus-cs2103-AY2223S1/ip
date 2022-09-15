package seedu.deku;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    protected LocalDate dueDate;
    protected String dueDateString;
    protected String dueTime;

    /**\
     * Creates a Deadline task that has a date but no time included.
     *
     * @param description description of Deadline.
     * @param dueDate Due date of Deadline.
     */
    public Deadline(String description, LocalDate dueDate, String dueDateString) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = "";
        this.dueDateString = dueDateString;
    }

    /**
     * Creates a Deadline task that has both a due date and due time.
     *
     * @param description description of Deadline.
     * @param dueDate Due date of Deadline.
     * @param dueTime Due time of Deadline.
     */
    public Deadline(String description, LocalDate dueDate, String dueTime, String dueDateString) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.dueDateString = dueDateString;
    }

    public String getDueDateInString() {
        return dueDateString;
    }

    /**
     * Returns String representation of due date, and due time if it is included.
     *
     * @return String of due date and time.
     */
    public String dueDateToString() {
        String dueDateString = this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String output;
        if (this.dueTime.equals("")) {
            output = String.format("%s", dueDateString);
        } else {
            output = String.format("%s, %s", dueDateString, this.dueTime);
        }
        return output;
    }

    public static Deadline reschedule(Deadline deadline, LocalDate rescheduledDate, String rescheduledTime,
                                      String dateTimeString) {
        return new Deadline(deadline.getDescription(), rescheduledDate, rescheduledTime, dateTimeString);
    }

    public static Deadline reschedule(Deadline deadline, LocalDate rescheduledDate, String dateTimeString) {
        return new Deadline(deadline.getDescription(), rescheduledDate, dateTimeString);
    }

    @Override
    public String toString() {
        String output = String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.dueDateToString());
        return output;
    }
}
