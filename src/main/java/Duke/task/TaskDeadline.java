package Duke.task;

import Duke.enums.Command;
import Duke.enums.SecondaryCommand;
import Duke.exceptions.InvalidDateException;
import Duke.utils.Utils;

import java.time.LocalDate;

/**
 * The {@code TaskDeadline} class stores relevant information for a taskDeadline.
 */
public class TaskDeadline extends Task {

    /**
     * The deadline for the task.
     */
    private final LocalDate taskBy;

    /**
     * Constructor for a task deadline.
     *
     * @param taskName a string representing the name of the task.
     * @param taskBy   a string representing the deadline for the task.
     */
    public TaskDeadline(String taskName, String taskBy) throws InvalidDateException {
        super(taskName);
        this.taskBy = Utils.formatStringToDate(taskBy);
    }

    /**
     * Constructor for a task deadline.
     *
     * @param taskName a string representing the name of the task.
     * @param taskBy   a string representing the deadline for the task.
     * @param done     a boolean representing if the task is done.
     */
    public TaskDeadline(String taskName, String taskBy, boolean done) throws InvalidDateException {
        super(taskName, done);
        this.taskBy = Utils.formatStringToDate(taskBy);
    }

    /**
     * Returns string representation of a taskDeadline.
     *
     * @return a string representing the taskDeadline.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), Utils.formatDateToString(this.taskBy));
    }

    /**
     * Returns a string representation to store task deadline in a file.
     *
     * @return The string representation of a task deadline for storage.
     */
    @Override
    public String toStorageString() {
        return String.format("%s %s %s %s\n%s",
                Command.DEADLINE.getValue(),
                getTaskName(),
                SecondaryCommand.BY.getValue(),
                taskBy,
                super.toStorageString());
    }
}
