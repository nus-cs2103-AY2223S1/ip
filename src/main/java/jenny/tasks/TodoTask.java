package jenny.tasks;

import jenny.exceptions.TaskException;

/**
 * A TodoTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class TodoTask extends AbstractTask {
    private static final String MESSAGE_SCOPE = TodoTask.class.getSimpleName();

    /**
     * Constructor of a TodoTask.
     *
     * @param description description of the task.
     */
    public TodoTask(String description) {
        super(description);
        if (description.equals("")) {
            throw new TaskException(String.format("%s: The description of this task cannot be empty.", MESSAGE_SCOPE));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
