package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Nullable;

/**
 * A to do task is a task that does not have any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructs a to-do task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a to-do task with the specified description and completion status.
     *
     * @param description The description of the to-do task.
     * @param completion Whether the to-do task has been completed.
     * @param completionDateTime The datetime when the task was marked completed.
     */
    public ToDo(String description, boolean completion, @Nullable LocalDateTime completionDateTime) {
        super(description, completion, completionDateTime);
    }

    @Override
    public ToDo edit(String userEditInput) {
        description = userEditInput;
        return this;
    }

    /**
     * Checks whether the to do task is active during the specified date.
     * Will return true if not done since to do tasks have no associated date attached,
     * and thus, will be assumed to be active if it is not yet completed.
     *
     * @param date The date to check whether the task is active.
     * @return Whether the to do task is active, true if it is not completed, else false.
     */
    @Override
    public boolean isActive(LocalDate date) {
        return !isDone;
    }

    /**
     * Parses the to-do into a savable string format, ready to be written to the hard disk.
     *
     * @return Savable string representation of the To-Do Task.
     */
    @Override
    public String toSaveFormat() {
        String completionStatus = this.isDone ? "Y" : "N";
        // escape instances of deliminator in task description
        String escapedDescription = this.description.replace("|", "\\|");
        return String.format("T | %s | %s | %s", completionStatus, escapedDescription, completionDateTime);
    }

    /**
     * Returns a string representation for the to-do task, prefixed with a [T],
     * followed by the task status, and the task description.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
