package duke;

/**
 * Describes all the exception handled by the program.
 */
public class DukeException extends Exception {

    private static final String EMPTY_INPUT = "Please enter something!";
    private static final String FILE_NOT_FOUND = "I cannot find your file";
    private static final String INVALID_INDEX = "The task number is invalid";
    private static final String INVALID_FORMAT = "The task format is invalid";
    private static final String INVALID_DATE_FORMAT = "The date format is invalid";
    private static final String UNKNOWN_COMMAND = "I don't know what that means";

    /**
     * Constructs a {@link DukeException} object using a specific message.
     *
     * @param message String message to be shown to the user.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Handles errors where the user input is empty.
     *
     * @return {@link DukeException}
     */
    public static DukeException dukeEmptyInputException() {
        return new DukeException(EMPTY_INPUT);
    }

    /**
     * Handles errors where the specified file cannot be found.
     *
     * @return {@link DukeException}
     */
    public static DukeException dukeFileNotFoundException() {
        return new DukeException(FILE_NOT_FOUND);
    }

    /**
     * Handles errors where the task number is out of range or is not an integer.
     *
     * @return {@link DukeException}
     */
    public static DukeException dukeInvalidIndexException() {
        return new DukeException(INVALID_INDEX);
    }

    /**
     * Handles errors where the task format of the input is incorrect.
     *
     * @return {@link DukeException}
     */
    public static DukeException dukeInvalidFormatException() {
        return new DukeException(INVALID_FORMAT);
    }

    /**
     * Handles errors where the date format of the task is incorrect.
     *
     * @return {@link DukeException}
     */
    public static DukeException dukeInvalidDateFormatException() {
        return new DukeException(INVALID_DATE_FORMAT);
    }

    /**
     * Handles errors where the command is not known by the program.
     *
     * @return {@link DukeException}
     */
    public static DukeException dukeUnknownCommandException() {
        return new DukeException(UNKNOWN_COMMAND);
    }
}
