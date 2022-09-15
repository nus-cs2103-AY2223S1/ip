package exceptions;

/**
 * An exception that indicates error while loading tasks from file.
 */
public class IncorrectFileInputException extends ByuException {

    @Override
    public String getMessage() {
        return "Incorrect input from file!";
    }
}
