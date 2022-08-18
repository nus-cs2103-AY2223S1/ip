package TaskItems;

import Exceptions.DeadlineTaskException;

/**
 * A DeadlineTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class DeadlineTask extends TaskItem {
    private final String dueDate;

    /**
     * Constructor for TaskItems.DeadlineTask.
     * @param description description of the task.
     * @param dueDate due date of the task.
     */
    public DeadlineTask(String description, String dueDate) {
        super(description);
        if (description != null && description.trim().isEmpty()) {
            throw new DeadlineTaskException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (dueDate != null && dueDate.trim().isEmpty()) {
            throw new DeadlineTaskException("☹ OOPS!!! The due date of a deadline cannot be empty.");
        }
        this.dueDate = dueDate;
    }

    /**
     * Returns the description of the task as a string.
     * @return the description of the task as a string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
