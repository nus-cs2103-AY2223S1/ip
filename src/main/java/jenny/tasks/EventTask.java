package jenny.tasks;

import jenny.exceptions.TaskException;

/**
 * A EventTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class EventTask extends AbstractTask {
    private static final String MESSAGE_SCOPE = EventTask.class.getSimpleName();
    private final String dueDate;

    /**
     * {@inheritDoc}
     *
     * @param dueDate due date of the task.
     */
    public EventTask(String description, String dueDate) {
        super(description);
        if (description != null && description.trim().isEmpty()) {
            throw new TaskException(String.format("%s: The description of this task cannot be empty.", MESSAGE_SCOPE));
        } else if (dueDate != null && dueDate.trim().isEmpty()) {
            throw new TaskException(String.format("%s: The due date of this task cannot be empty.", MESSAGE_SCOPE));
        }
        this.dueDate = dueDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String save() {
        return String.format("%s,%s,%s,%s", MESSAGE_SCOPE, this.isDone, this.description, this.dueDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dueDate + ")";
    }
}
