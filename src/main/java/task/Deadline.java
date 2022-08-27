package task;

import date.DeadlineDateTime;

/**
 * Represents a task with a deadline
 *
 * @author Bryan Lim Jing Xiang
 */
public class Deadline extends Task {
    private final DeadlineDateTime deadline;

    /**
     * {@inheritDoc}
     *
     * @param deadline Deadline of the task
     */
    public Deadline(String taskItem, DeadlineDateTime deadline) {
        super(taskItem);
        this.deadline = deadline;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String deadlineDisplay = String.format(" (by: %s)", this.deadline);
        return "[D]" + super.toString() + deadlineDisplay;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        int markedStatus = getIsMarked() ? 1 : 0;
        return String.format("D,%d,%s,%s\n", markedStatus, getTaskItem(), deadline.encode());
    }
}
