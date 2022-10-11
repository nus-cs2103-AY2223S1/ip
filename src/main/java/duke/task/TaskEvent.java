package duke.task;

import duke.enums.Command;
import duke.enums.SecondaryCommand;
import duke.exceptions.InvalidDateException;
import duke.utils.Utils;

import java.time.LocalDate;

/**
 * The {@code TaskEvent} class stores relevant information for a taskEvent.
 */
public class TaskEvent extends Task {

    private final LocalDate taskAt; // The date the task is supposed to occur at.

    /**
     * Constructs a new task event.
     *
     * @param taskName a string representing the name of the task.
     * @param at       a string representing the time of the task.
     */
    public TaskEvent(String taskName, String at) throws InvalidDateException {
        super(taskName);
        taskAt = Utils.formatStringToDate(at);
    }

    /**
     * Constructs a task event specifying whether it is done.
     *
     * @param taskName a string representing the name of the task.
     * @param at       a string representing the time of the task.
     * @param done     a boolean representing if the task is done.
     */
    public TaskEvent(String taskName, String at, boolean done) throws InvalidDateException {
        super(taskName, done);
        taskAt = Utils.formatStringToDate(at);
    }

    /**
     * Returns a string representing the taskEvent.
     *
     * @return a string representing the taskEvent.
     */
    @Override
    public String toString() {
        return String.format("[E] %s \n(at: %s)", super.toString(), Utils.formatDateToString(taskAt));
    }

    /**
     * Returns a string representing of a task event for storage.
     *
     * @return a string representing of a task event for storage.
     */
    @Override
    public String toStorageString() {
        return String.format("%s %s %s %s\n%s",
                Command.EVENT.getValue(),
                getTaskName(),
                SecondaryCommand.AT.getValue(),
                taskAt,
                super.toStorageString());
    }
}
