package jenny.exceptions;

import jenny.tasks.AbstractTask;

/**
 * Represents exceptions thrown in the {@link AbstractTask AbstrackTask} class and its members.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class TaskException extends IllegalArgumentException {
    /**
     * Creates an instance of the exception.
     *
     * @param errorMessage error message of the exception.
     */
    public TaskException(String errorMessage) {
        super(errorMessage);
    }
}
