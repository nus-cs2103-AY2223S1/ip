package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a Deadline, which is a Task with a deadline.
 */
public class Deadline extends Task {
    protected final LocalDate deadline;

    /**
     * Constructor of Deadline with description and deadline.
     *
     * @param description Description of the Task.
     * @param deadline Deadline of the Task.
     * @throws DukeException If date format is incorrect.
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format!");
        }
        type = 'D';
    }

    /**
     * Constructor of Deadline with description, boolean to set the Deadline as done or not done, and deadline.
     *
     * @param description Description of the Task.
     * @param isDone Boolean to set the Task as done or not done.
     * @param deadline Deadline of the Task.
     * @throws DukeException If date format is incorrect.
     */
    public Deadline(String description, boolean isDone, String deadline) throws DukeException {
        this(description, deadline);
        this.isDone = isDone;
    }

    /**
     * Returns the String representation of the Deadline for UI.
     *
     * @return String representation of the Deadline for UI.
     */
    @Override
    public String toString() {
        assert deadline != null;
        return super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the String representation of the Deadline for Storage.
     *
     * @return String representation of the Deadline for Storage.
     */
    @Override
    public String toData() {
        return super.toData() + ", " + deadline;
    }

    /**
     * Checks equality to another Object.
     *
     * @param o Other Object.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Deadline deadline1 = (Deadline) o;
        return Objects.equals(deadline, deadline1.deadline);
    }
}
