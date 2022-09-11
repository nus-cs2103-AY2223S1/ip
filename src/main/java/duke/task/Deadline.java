package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Duke application.
 * Deadline is a Task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    /** Deadline of the deadline. */
    private final LocalDateTime deadline;

    /**
     * Constructs a deadline with description and deadline.
     * Deadline is set as "not done" when created.
     *
     * @param description Description of a deadline.
     * @param deadline    Deadline of a deadline.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Creates a Deadline with done, description and date.
     *
     * @param done        Whether the Deadline is done.
     * @param description Description of Deadline.
     * @param date        Date of Deadline.
     * @return Deadline object with the given parameters.
     */
    public static Deadline create(String done, String description, String date) {
        Deadline deadline = new Deadline(description, LocalDateTime.parse(date));
        if (done.equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    /**
     * Gets the Deadline in a format for file saving.
     *
     * @return Deadline in file saving format.
     */
    public String getFileFormat() {
        return String.format("D | %s | %s", super.getFileFormat(), deadline);
    }

    /**
     * Gets the string representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy HH:mm");
        String deadline = this.deadline.format(formatter);
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
