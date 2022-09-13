package exceptions;

/**
 * An exception that indicates error while loading tasks from file.
 */
public class IncorrectFileInputException extends DukeException {

    @Override
    public String getMessage() {
        return "Incorrect input from file!";
    }
}
