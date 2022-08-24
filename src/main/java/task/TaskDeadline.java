package task;

import enums.Command;
import enums.SecondaryCommand;

/**
 * The {@code TaskDeadline} class stores relevant information for a taskDeadline.
 */
public class TaskDeadline extends Task {

    /**
     * The deadline for the task.
     */
    private final String taskBy;

    /**
     * Constructor for a task deadline.
     *
     * @param taskName a string representing the name of the task.
     * @param taskBy   a string representing the deadline for the task.
     */
    public TaskDeadline(String taskName, String taskBy) {
        super(taskName);
        this.taskBy = taskBy;
    }

    /**
     * Constructor for a task deadline.
     *
     * @param taskName a string representing the name of the task.
     * @param taskBy   a string representing the deadline for the task.
     * @param done     a boolean representing if the task is done.
     */
    public TaskDeadline(String taskName, String taskBy, boolean done) {
        super(taskName, done);
        this.taskBy = taskBy;
    }

    /**
     * Returns string representation of a taskDeadline.
     *
     * @return a string representing the taskDeadline.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.taskBy);
    }

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
