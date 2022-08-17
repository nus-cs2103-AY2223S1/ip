public class DukeException extends Exception{

    protected static final String EMPTY_INPUT = "Please enter something!";
    protected static final String EMPTY_NAME = "Please give a name for the task";
    protected static final String UNKNOWN_COMMAND = "I don't know what that means";
    protected static final String INVALID_INDEX = "The task number is invalid";

    public DukeException(String message) {
        super(message);
    }

    public static DukeException DukeEmptyInputException() {
        return new DukeException(EMPTY_INPUT);
    }

    public static DukeException DukeEmptyNameException() {
        return new DukeException(EMPTY_NAME);
    }

    public static DukeException DukeUnknownCommandException() {
        return new DukeException(UNKNOWN_COMMAND);
    }

    public static DukeException DukeInvalidIndexException() {
        return new DukeException(INVALID_INDEX);
    }
}