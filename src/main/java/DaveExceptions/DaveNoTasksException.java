package DaveExceptions;

public class DaveNoTasksException extends DaveException {

    /**
     * Creates an exception for having no tasks in Dave 2.
     */
    public DaveNoTasksException() {
        super("Theres no tasks in your list! How about adding some first uwu");
    }

}
