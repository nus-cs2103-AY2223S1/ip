package bocil;

/**
 * Describes all the exception handled by the program.
 */
public class BocilException extends Exception {

    private static final String EMPTY_INPUT = "Please enter something!";
    private static final String FILE_NOT_FOUND = "I cannot find your file";
    private static final String INVALID_INDEX = "The task number is invalid";
    private static final String INVALID_FORMAT = "The task format is invalid";
    private static final String INVALID_DATE_FORMAT = "The date format is invalid";
    private static final String UNKNOWN_COMMAND = "I don't know what that means";

    /**
     * Constructs a {@link BocilException} object using a specific message.
     *
     * @param message String message to be shown to the user.
     */
    public BocilException(String message) {
        super(message);
    }

    /**
     * Handles errors where the user input is empty.
     *
     * @return {@link BocilException}
     */
    public static BocilException bocilEmptyInputException() {
        return new BocilException(EMPTY_INPUT);
    }

    /**
     * Handles errors where the specified file cannot be found.
     *
     * @return {@link BocilException}
     */
    public static BocilException bocilFileNotFoundException() {
        return new BocilException(FILE_NOT_FOUND);
    }

    /**
     * Handles errors where the task number is out of range or is not an integer.
     *
     * @return {@link BocilException}
     */
    public static BocilException bocilInvalidIndexException() {
        return new BocilException(INVALID_INDEX);
    }

    /**
     * Handles errors where the task format of the input is incorrect.
     *
     * @return {@link BocilException}
     */
    public static BocilException bocilInvalidFormatException() {
        return new BocilException(INVALID_FORMAT);
    }

    /**
     * Handles errors where the date format of the task is incorrect.
     *
     * @return {@link BocilException}
     */
    public static BocilException bocilInvalidDateFormatException() {
        return new BocilException(INVALID_DATE_FORMAT);
    }

    /**
     * Handles errors where the command is not known by the program.
     *
     * @return {@link BocilException}
     */
    public static BocilException bocilUnknownCommandException() {
        return new BocilException(UNKNOWN_COMMAND);
    }
}
