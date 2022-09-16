package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline that can be created by the user
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Constructor used to create a new deadline object
     * @param s Command containing task message and time
     * @param isCompleted whether the task is completed
     */
    public Deadline (String s , boolean isCompleted) {
        super(s.split("/by")[0].substring(8).strip(), isCompleted);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        date = LocalDateTime.parse(s.split("/by")[1].strip(), formatter);
    }

    public String toString() {
        String completion = this.isComplete() ? "[X]" : "[ ]";
        return "[D]" + completion + " " + this.getTaskName() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
    /**
     * Returns a formatted version of the task to store in memory
     * @return formatted string representation of task
     */
    public String toFormattedString() {
        String completion = this.isComplete() ? "1" : "0";
        return "D | " + completion + " | " + this.getTaskName() + " | "
                + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(date);
    }
}