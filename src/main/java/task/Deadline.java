package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates the idea of a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime date;

    /**
     * Creates a deadline.
     * @param description what is the task.
     * @param status whether it has been completed.
     * @param date when is the task due.
     */
    public Deadline(String description, boolean status, LocalDateTime date) {
        super(description, status);
        this.date = date;
    }

    /**
     * Converts the deadline to string representation to be stored in text file.
     * @return a string in format D | T | deadline | 2022-10-15T18:00
     */
    @Override
    public String getDescription() {
        String status = super.getStatus() ? "T" : "F";
        return "D | " + status + " | " + super.toString() + " | " + date.toString() + "\n";
    }

    /**
     * Converts the deadline to string representation for user.
     * @return a string in format [D][ ] deadline (by: 18:00 Oct 15 2022)
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.toString() +
                " (by: " + date.format(DateTimeFormatter.ofPattern("HH:mm MMM d yyyy")) + ")";
    }
}
