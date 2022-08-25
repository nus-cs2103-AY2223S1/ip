package jenny.tasks;

import jenny.exceptions.TaskException;

/**
 * A Deadline to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class Deadline extends AbstractTask {
    private final String dueDate;

    /**
     * Constructor for jenny.tasks.Deadline.
     * @param description description of the task.
     * @param dueDate due date of the task.
     */
    public Deadline(String description, String dueDate) {
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
        return String.format("%s,%s,%s,%s",
                this.getClass().getSimpleName(),
                this.isDone, this.description,
                this.dueDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
