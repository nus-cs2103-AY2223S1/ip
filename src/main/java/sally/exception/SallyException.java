package sally.exception;

/**
 * SallyException class throws checked exception when exceptions are thrown and caught in
 * the execution of Sally.
 *
 * @author liviamil
 */

public class SallyException extends Exception {
    /**
     * Constructor for SallyException with a given message.
     *
     * @param message for a specific exception.
     */
    public SallyException(String message) {
        super(message);
    }

    /**
     * Constructor for SallyException.
     */
    public SallyException() {};

    /**
     * Border used to print.
     */
    protected String BORDER ="-------------------------------------------------------------------------------------\n";

    /**
     * Throws task not found exception.
     */
    public static class SallyTaskNotFoundException extends SallyException {
        @Override
        public String toString() {
            return BORDER + "I  can't find this task number. You might want to check your list again!\n" + BORDER;
        }
    }

    /**
     * Throws no description exception for tasks
     */
    public static class SallyNoDescriptionException extends SallyException {
        @Override
        public String toString() {
            return BORDER + "Oops! The description cannot be empty.\n" + BORDER;
        }
    }

    /**
     * Throws invalid input exception throughout the program
     */
    public static class SallyInvalidInputException extends SallyException {
        @Override
        public String toString() {
            return BORDER + "Oops! I'm sorry, I don't understand that :(\n" + BORDER;
        }
    }

    /**
     * Throws no deadline description exception.
     */
    public static class SallyNoDeadlineException extends SallyException {
        @Override
        public String toString() {
            return BORDER + "Oops! sally.task.Deadline has to be followed by '/by' and deadline time\n" + BORDER;
        }
    }

    /**
     * Throws no venue exception for events.
     */
    public static class SallyNoPlaceException extends SallyException {
        @Override
        public String toString() {
            return BORDER + "Oops! sally.task.Event has to be followed by '/at' and event place\n" + BORDER;
        }
    }
}
