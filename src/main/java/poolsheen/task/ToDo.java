package poolsheen.task;

import poolsheen.PoolsheenException;

/**
 * Represents a ToDo task for Poolsheen to remember.
 */
public class ToDo extends Task {
    /**
     * A public constructor to initialise a ToDo task.
     *
     * @param description The details of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public void setTime(String newTime) {
        throw new PoolsheenException("ToDo tasks have no time", "update", "Try again!");
    }

    @Override
    public String[] toArr() {
        return new String[] {"T", this.getStatusIcon(), this.getDescription()};
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
