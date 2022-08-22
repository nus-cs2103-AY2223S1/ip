import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    /**
     * Constructor for a deadline instance.
     *
     * @param description the description of the task
     * @param by the due date of the task
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Invalid Date Format, please input it as YYYY-MM-DD");
        }
    }

    /**
     * String representation of the deadline.
     *
     * @return String representing this task
     */
    @Override
    public String toString() {
        return "[D]" +
                super.toString() +
                " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ")";
    }

    /**
     * Gets the deadline of the Task, if any.
     *
     * @return A LocalDate representing the Task's deadline if it exists
     *         null if no such deadline exists
     */
    @Override
    public LocalDate getDeadline() {
        return this.by;
    }
}
