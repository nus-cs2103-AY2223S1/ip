package Duke.task;

import Duke.enums.Command;
import Duke.enums.SecondaryCommand;
import Duke.exceptions.InvalidDateException;
import Duke.utils.Utils;

import java.time.LocalDate;

/**
 * The {@code TaskEvent} class stores relevant information for a taskEvent.
 */
public class TaskEvent extends Task {

    /**
     * The time the task is supposed to occur.
     */
    private final LocalDate taskAt;

    /**
     * Constructor for a task event.
     *
     * @param taskName a string representing the name of the task.
     * @param taskAt   a string representing the time of the task.
     */
    public TaskEvent(String taskName, String taskAt) throws InvalidDateException {
        super(taskName);
        this.taskAt = Utils.formatStringToDate(taskAt);
    }

    /**
     * Constructor for a task event.
     *
     * @param taskName a string representing the name of the task.
     * @param taskAt   a string representing the time of the task.
     * @param done     a boolean representing if the task is done.
     */
    public TaskEvent(String taskName, String taskAt, boolean done) throws InvalidDateException {
        super(taskName, done);
        this.taskAt = Utils.formatStringToDate(taskAt);
    }

    /**
     * Returns string representation of a taskEvent.
     *
     * @return The string representing the taskEvent.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), Utils.formatDateToString(this.taskAt));
    }

    /**
     * Returns a string representation to store task event in a file.
     *
     * @return The string representation of a task event for storage.
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
