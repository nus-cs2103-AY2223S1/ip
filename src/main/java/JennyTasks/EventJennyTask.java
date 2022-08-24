package JennyTasks;

import Exceptions.EventTaskException;

/**
 * A EventJennyTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class EventJennyTask extends JennyTask {
    private final String dueDate;

    /**
     * Constructor of a EventJennyTask.
     * @param description description of the task.
     * @param dueDate due date of the task.
     */
    public EventJennyTask(String description, String dueDate) {
        super(description);
        if (description != null && description.trim().isEmpty()) {
            throw new EventTaskException("☹ OOPS!!! The description of a event cannot be empty.");
        } else if (dueDate != null && dueDate.trim().isEmpty()) {
            throw new EventTaskException("☹ OOPS!!! The due date of a event cannot be empty.");
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
                this.isDone,
                this.description,
                this.dueDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dueDate + ")";
    }
}
