package task;

import exceptions.InvalidDateException;
import utils.Utils;

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
     * Returns string representation of a taskDeadline.
     *
     * @return a string representing the taskDeadline.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), Utils.formatDateToString(this.taskBy));
    }
}
