package chatbot.exceptions;

/**
 * The DukeException class represents all possible exceptions
 * that can occur during user's interaction with chatbot.
 */
public class DukeException extends Exception {
    public static final DukeException TASK_INDEX_MISSING = new DukeException("Do tell me the index...");
    public static final DukeException INVALID_INDEX_FORMAT = new DukeException("HELLO do you know index is a number");
    public static final DukeException CORRUPTED_DATA = new DukeException("You have corrupted the data I saved");
    public static final DukeException INSUFFICIENT_TASK_SPECIFICATION = new DukeException("Please your task lacks the necessary specifications");
    public static final DukeException INVALID_COMMAND = new DukeException("Your command lacks the keyword for me to act upon");
    public static final DukeException INVALID_TASK_INDEX = new DukeException("Read the index of the existing tasks carefully...");
    public static final DukeException INVALID_DATE_FORMAT = new DukeException("Your date is rubbish");

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "\t" + super.getMessage();
    }
}
