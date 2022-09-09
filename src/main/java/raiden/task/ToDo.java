package raiden.task;

import raiden.RaidenException;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo task with the given description.
     *
     * @param description The description of this ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of this ToDo task.
     *
     * @return The String representation of this ToDo task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.T + "]" + super.toString();
    }

    /**
     * Returns the String representation of the command to initialise this ToDo task.
     *
     * @return The String representation of the command for this ToDo task.
     */
    @Override
    public String toCommand() {
        return TaskType.T + " | " + super.toCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String changeDate(String newDate) throws RaidenException {
        throw new RaidenException("ToDo tasks do not have date and time!");
    }
}
