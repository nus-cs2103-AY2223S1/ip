package TaskItems;

import Exceptions.EventTaskException;

/**
 * A EventTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class EventTask extends TaskItem {
    private final String dueDate;

    /**
     * Constructor of a EventTask.
     * @param description description of the task.
     * @param dueDate due date of the task.
     */
    public EventTask(String description, String dueDate) {
        super(description);
        if (description != null && description.trim().isEmpty()) {
            throw new EventTaskException("☹ OOPS!!! The description of a event cannot be empty.");
        } else if (dueDate != null && dueDate.trim().isEmpty()) {
            throw new EventTaskException("☹ OOPS!!! The due date of a event cannot be empty.");
        }
        this.dueDate = dueDate;
    }

    /**
     * Returns the description of the task as a string.
     * @return the description of the task as a string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dueDate + ")";
    }
}
