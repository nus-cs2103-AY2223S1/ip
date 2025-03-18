package Duke;

/**
 * Exception class to create more specific exceptions for Duke.
 */
public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Exception raised when todo task is empty
     */
    public static class EmptyTaskException extends DukeException {
        public EmptyTaskException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Exception raised when command is not todo/deadline/event
     */
    public static class UnkownCommandException extends DukeException {

        public UnkownCommandException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Exception raised when parameters contain invalid inputs.
     */
    public static class InvalidParameterException extends DukeException {
        public InvalidParameterException(String errorMessage) {
            super(errorMessage);
        }
    }

}
