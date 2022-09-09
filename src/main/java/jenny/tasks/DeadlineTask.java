package jenny.tasks;

import jenny.exceptions.JennyException;

import java.time.LocalDate;


/**
 * Represents a deadline task to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class DeadlineTask extends Task {
    private static final String MESSAGE_SCOPE = DeadlineTask.class.getSimpleName();
    private final LocalDate dueDate;

    /**
     * Constructor for an instance of a new deadline task.
     * Will initialise a new task with the provided {@code description} and {@code dueDate}.
     * By default, the task is marked as incomplete.
     *
     * @param description a string to describe the task.
     * @param dueDate due date of the task.
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application.
     */
    public DeadlineTask(String description, LocalDate dueDate) throws JennyException {
        super(description);
        boolean isEmpty = description.trim().isEmpty();
        boolean isNull = dueDate == null;
        if (isEmpty) {
            assert isEmpty;
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_DESCRIPTION);
        } else if (isNull) {
            assert isNull;
            throw new JennyException(MESSAGE_SCOPE, ERROR_INVALID_DUE_DATE);
        }
        assert !isEmpty && !isNull;
        this.dueDate = dueDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String save() {
        return String.format("%s,%s,%s,%s", MESSAGE_SCOPE, this.isDone, this.getDescription(), this.dueDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
