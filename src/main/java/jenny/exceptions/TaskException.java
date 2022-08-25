package jenny.exceptions;

import jenny.tasks.AbstractTask;

/**
 * Encapsulates an instance to represent exceptions thrown in the {@link AbstractTask} class.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class TaskException extends IllegalArgumentException {
    /**
     * Constructor of a TaskException.
     * @param errorMessage error message of the exception.
     */
    public TaskException(String errorMessage) {
        super(errorMessage);
    }
}
