package jenny.tasks;

import jenny.exceptions.JennyException;

/**
 * A TodoTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class NoteTask extends Task {
    private static final String MESSAGE_SCOPE = NoteTask.class.getSimpleName();
    private static final String ERROR_MISSING_DESCRIPTION = "The contents of this note cannot be empty.";

    /**
     * Constructor of a TodoTask.
     *
     * @param description description of the task.
     */
    public NoteTask(String description) throws JennyException {
        super(description);
        if (description.equals("")) {
            throw new JennyException(MESSAGE_SCOPE, ERROR_MISSING_DESCRIPTION);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String save() {
        return String.format("%s,%s,%s", MESSAGE_SCOPE, this.isDone, this.getDescription());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[N]" + super.toString();
    }
}
