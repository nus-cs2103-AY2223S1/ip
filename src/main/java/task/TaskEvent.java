package task;

import enums.Command;
import enums.SecondaryCommand;

/**
 * The {@code TaskEvent} class stores relevant information for a taskEvent.
 */
public class TaskEvent extends Task {

    /**
     * The time the task is supposed to occur.
     */
    private final String taskAt;

    /**
     * Constructor for a task event.
     *
     * @param taskName a string representing the name of the task.
     * @param taskAt   a string representing the time of the task.
     */
    public TaskEvent(String taskName, String taskAt) {
        super(taskName);
        this.taskAt = taskAt;
    }

    /**
     * Constructor for a task event.
     *
     * @param taskName a string representing the name of the task.
     * @param taskAt   a string representing the time of the task.
     * @param done     a boolean representing if the task is done.
     */
    public TaskEvent(String taskName, String taskAt, boolean done) {
        super(taskName, done);
        this.taskAt = taskAt;
    }

    /**
     * Returns string representation of a taskEvent.
     *
     * @return a string representing the taskEvent.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.taskAt);
    }

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
