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

    public DukeException(String message) {
        super(message);
    }

    public static DukeException dukeEmptyInputException() {
        return new DukeException(EMPTY_INPUT);
    }

    public static DukeException dukeFileNotFoundException() {
        return new DukeException(FILE_NOT_FOUND);
    }

    public static DukeException dukeInvalidIndexException() {
        return new DukeException(INVALID_INDEX);
    }

    public static DukeException dukeInvalidFormatException() {
        return new DukeException(INVALID_FORMAT);
    }

    public static DukeException dukeInvalidDateFormatException() {
        return new DukeException(INVALID_DATE_FORMAT);
    }

    public static DukeException dukeUnknownCommandException() {
        return new DukeException(UNKNOWN_COMMAND);
    }
}
