package JennyTasks;

import Exceptions.DeadlineJennyTaskException;

/**
 * A DeadlineJennyTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class DeadlineJennyTask extends JennyTask {
    private final String dueDate;

    /**
     * Constructor for JennyTasks.DeadlineJennyTask.
     * @param description description of the task.
     * @param dueDate due date of the task.
     */
    public DeadlineJennyTask(String description, String dueDate) {
        super(description);
        if (description != null && description.trim().isEmpty()) {
            throw new DeadlineJennyTaskException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (dueDate != null && dueDate.trim().isEmpty()) {
            throw new DeadlineJennyTaskException("☹ OOPS!!! The due date of a deadline cannot be empty.");
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
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
