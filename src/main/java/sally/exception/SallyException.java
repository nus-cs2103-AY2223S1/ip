package sally.exception;

/**
 * SallyException class throws checked exception when exceptions are thrown and caught in
 * the execution of Sally.
 *
 * @author liviamil
 */

public class SallyException extends Exception {
    public SallyException(String message) {
        super(message);
    }

    public SallyException() {};

    protected String BORDER ="-------------------------------------------------------------------------------------\n";

    public static class SallyTaskNotFoundException extends SallyException {
        @Override
        public String toString() {
            return BORDER + "I  can't find this task number. You might want to check your list again!\n" + BORDER;
        }
    }

    public static class SallyNoDescriptionException extends SallyException {
        @Override
        public String toString() {
            return BORDER + "Oops! The description cannot be empty.\n" + BORDER;
        }
    }

    public static class SallyInvalidInputException extends SallyException {
        @Override
        public String toString() {
            return BORDER + "Oops! I'm sorry, I don't understand that :(\n" + BORDER;
        }
    }

    public static class SallyNoDeadlineException extends SallyException {
        @Override
        public String toString() {
            return BORDER + "Oops! sally.task.Deadline has to be followed by '/by' and deadline time\n" + BORDER;
        }
    }

    public static class SallyNoPlaceException extends SallyException {
        @Override
        public String toString() {
            return BORDER + "Oops! sally.task.Event has to be followed by '/at' and event place\n" + BORDER;
        }
    }
}
