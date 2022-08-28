package task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * This class encapsulates the idea of a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime DATE;

    /**
     * Creates a deadline.
     *
     * @param description what is the task.
     * @param status      whether it has been completed.
     * @param DATE        when is the task due.
     */
    public Deadline(String description, boolean status, LocalDateTime DATE) {
        super(description, status);
        this.DATE = DATE;
    }

    /**
     * Converts the deadline to string representation to be stored in text file.
     *
     * @return a string in format D | T | deadline | 2022-10-15T18:00
     */
    @Override
    public String getDESCRIPTION() {
        String status = super.getStatus() ? "T" : "F";
        return "D | " + status + " | " + super.toString() + " | " + DATE.toString() + "\n";
    }

    /**
     * Converts the deadline to string representation for user.
     *
     * @return a string in format [D][ ] deadline (by: 18:00 Oct 15 2022)
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.toString() +
                " (by: " + DATE.format(DateTimeFormatter.ofPattern("HH:mm MMM d yyyy")) + ")";
    }
}
