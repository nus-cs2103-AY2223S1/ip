package duke;

public class DukeException extends Exception{

    protected static final String EMPTY_INPUT = "Please enter something!";
    protected static final String FILE_NOT_FOUND = "I cannot find your file";
    protected static final String INVALID_INDEX = "The task number is invalid";
    protected static final String INVALID_FORMAT = "The task format is invalid";
    protected static final String INVALID_DATE_FORMAT = "The date format is invalid";
    protected static final String UNKNOWN_COMMAND = "I don't know what that means";

    public DukeException(String message) {
        super(message);
    }

    public static DukeException DukeEmptyInputException() {
        return new DukeException(EMPTY_INPUT);
    }

    public static DukeException DukeFileNotFoundException() {
        return new DukeException(FILE_NOT_FOUND);
    }

    public static DukeException DukeInvalidIndexException() {
        return new DukeException(INVALID_INDEX);
    }

    public static DukeException DukeInvalidFormatException() {
        return new DukeException(INVALID_FORMAT);
    }

    public static DukeException DukeInvalidDateFormatException() {
        return new DukeException(INVALID_DATE_FORMAT);
    }

    public static DukeException DukeUnknownCommandException() {
        return new DukeException(UNKNOWN_COMMAND);
    }
}