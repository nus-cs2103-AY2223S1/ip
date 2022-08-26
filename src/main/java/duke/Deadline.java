package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;
    protected String deadlineString;

    /**
     * Constructs a <code>Deadline</code> task.
     *
     * @param description Description of the task.
     * @param deadline Date and Time of the deadline in "dd/MM/yyyy HHmm" format.
     * @param isDone Indicator whether the task has been done or not.
     */
    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadlineString = deadline;
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Returns the hour and minutes of this <code>Deadline</code> in a <code>String</code> in "HHmm" format.
     *
     * @return <code>String</code> representation of the hour and minutes of this <code>Deadline</code>.
     */
    public String getTime() {
        int len = deadlineString.length();
        return deadlineString.substring(len - 4);
    }

    /**
     * Returns the <code>String</code> representation of this <code>Deadline</code>.
     *
     * @return <code>String</code> representation of this <code>Deadline</code>.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getDayOfWeek() + " " + deadline.getDayOfMonth() + " " +
                deadline.getMonth() + " " + deadline.getYear() + " " + this.getTime() + ")";
    }

    /**
     * Returns the <code>String</code> representation of this <code>Deadline</code> in the format to be stored in the local
     * storage.
     *
     * @return <code>String</code> representation of this <code>Deadline</code> in the format to be stored in the local storage.
     */
    public String toStorageFormat() {
        char done = isDone ? '1' : '0';
        return "D" + " | " + done + " | " + this.description + " | " + this.deadlineString + "\n";
    }
}
