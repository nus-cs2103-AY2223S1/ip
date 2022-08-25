package jenny.tasks;

import jenny.exceptions.TaskException;

/**
 * A Event to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class Event extends AbstractTask {
    private final String dueDate;

    /**
     * Constructor of a Event.
     *
     * @param description description of the task.
     * @param dueDate     due date of the task.
     */
    public Event(String description, String dueDate) {
        super(description);
        if (description != null && description.trim().isEmpty()) {
            throw new TaskException(String.format("%s: The description of this task cannot be empty.",
                    this.getClass().getSimpleName()));
        } else if (dueDate != null && dueDate.trim().isEmpty()) {
            throw new TaskException(String.format("%s: The due date of this task cannot be empty.",
                    this.getClass().getSimpleName()));
        }
        this.dueDate = dueDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String save() {
        return String.format("%s,%s,%s,%s", this.getClass().getSimpleName(), this.isDone, this.description, this.dueDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dueDate + ")";
    }
}
