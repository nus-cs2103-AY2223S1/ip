package duke.task;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Duke application.
 * Deadline is a Task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    /** Exception due to wrong deadline format. */
    public final static DukeException wrongFormat =
        new DukeException("Wrong format for Deadline!\nShould be 'deadline <description> /by YYYY-MM-DD'.");

    /** Deadline of the deadline. */
    private final LocalDate deadline;

    /**
     * Constructor for a deadline that takes in description and deadline.
     * Deadline is set as "not done" when created.
     *
     * @param description Description of a deadline.
     * @param deadline    Deadline of a deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Factory method for a Deadline that takes in done, description and date.
     *
     * @param done        Whether the Deadline is done.
     * @param description Description of Deadline.
     * @param date        Date of Deadline.
     * @return Deadline object with the given parameters.
     */
    public static Deadline create(String done, String description, String date) {
        Deadline deadline = new Deadline(description, LocalDate.parse(date));
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
        return String.format("D | %s | %s", super.getFileFormat(), this.deadline);
    }

    /**
     * Gets the string representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy");
        String deadline = this.deadline.format(formatter);
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
