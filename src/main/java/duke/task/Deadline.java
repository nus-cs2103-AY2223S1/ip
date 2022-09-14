package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline which is ane type of task.
 */
public class Deadline extends Task {
    private LocalDate deadlineDate;

    /**
     * Constructs a new Deadline instance with a description and a date.
     *
     * @param description Description of the deadline.
     * @param deadlineDate Date of the deadline.
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Checks if a deadline has a matching date.
     *
     * @param localDate Given date.
     * @return True if the deadline has the given date; false otherwise.
     */
    @Override
    public boolean matchDate(LocalDate localDate) {
        return deadlineDate.equals(localDate);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns if a given object is equal to a Deadline instance.
     *
     * @param object Given object.
     * @return True if the two are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Deadline) {
            Deadline deadline = (Deadline) object;
            if (this == deadline) {
                return true;
            } else {
                boolean isSameDescription = this.description.equals(deadline.description);
                boolean isSameDate = this.deadlineDate.equals(deadline.deadlineDate);
                return isSameDescription && isSameDate;
            }
        } else {
            return false;
        }
    }
}
