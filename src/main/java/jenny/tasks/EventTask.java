package jenny.tasks;

import jenny.exceptions.JennyException;
import jenny.exceptions.TaskException;

import java.time.LocalDate;

/**
 * A EventTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class EventTask extends AbstractTask {
    private static final String MESSAGE_SCOPE = EventTask.class.getSimpleName();
    private final LocalDate dueDate;

    /**
     * {@inheritDoc}
     *
     * @param dueDate due date of the task.
     */
    public EventTask(String description, LocalDate dueDate) {
        super(description);
        if (description.trim().isEmpty()) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_DESCRIPTION);
        } else if (dueDate == null) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_DUE_DATE);
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
