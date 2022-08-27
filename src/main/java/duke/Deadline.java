package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline is a Task that also has a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    
    public static final String ENCODED_TASK_TYPE = "D";

    /**
     * Returns a Deadline instance with the given description and the deadline.
     *
     * @param description The Deadline description.
     * @param deadline The deadline by which the task needs to be completed.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm");
        return dTF.format(deadline).toString();
    }

    /**
     * Encodes the deadline as the same string representation that the user entered.
     * Removes the T to match that representation.
     *
     * @return The encoded string representation of the deadline.
     */
    public String encodeDeadline() {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return deadline.toString().replaceAll("T", " ");
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return String.format("[D] %s %s (by: %s)", getStatusIcon(), getDescription(), 
        getDeadline());
    }

    /**
     * Returns an encoded string representation of the Deadline instance to be written to the file.
     *
     * @return The string representation of the Deadline instance written to the file
     */
    @Override
    public String encode() {
        return String.format("%s|%d|%s|%s\n", ENCODED_TASK_TYPE, getIsDone() ? 1 : 0, getDescription(), encodeDeadline());
    }
}
