package task;

import exceptions.InvalidDateException;
import utils.Utils;

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
     * Returns string representation of a taskEvent.
     *
     * @return a string representing the taskEvent.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), Utils.formatDateToString(this.taskAt));
    }
}
