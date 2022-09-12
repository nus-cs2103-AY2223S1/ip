package duke.task;

import duke.enums.Command;
import duke.enums.SecondaryCommand;
import duke.exceptions.InvalidDateException;
import duke.utils.Utils;

import java.time.LocalDate;

/**
 * The {@code TaskDeadline} class stores relevant information for a taskDeadline.
 */
public class TaskDeadline extends Task {

    private final LocalDate taskBy; // The date the task is supposed to be completed by.

    /**
     * Constructs a new task deadline.
     *
     * @param taskName a string representing the name of the task.
     * @param by       a string representing the deadline for the task.
     */
    public TaskDeadline(String taskName, String by) throws InvalidDateException {
        super(taskName);
        taskBy = Utils.formatStringToDate(by);
    }

    /**
     * Constructs a task deadline specifying whether it is done.
     *
     * @param taskName a string representing the name of the task.
     * @param by       a string representing the deadline for the task.
     * @param done     a boolean representing if the task is done.
     */
    public TaskDeadline(String taskName, String by, boolean done) throws InvalidDateException {
        super(taskName, done);
        taskBy = Utils.formatStringToDate(by);
    }

    /**
     * Returns a string representing the taskDeadline.
     *
     * @return a string representing the taskDeadline.
     */
    @Override
    public String toString() {
        return String.format("[D] %s \n(by: %s)", super.toString(), Utils.formatDateToString(taskBy));
    }

    /**
     * Returns a string representing of a task deadline for storage.
     *
     * @return a string representing a task deadline for storage.
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
