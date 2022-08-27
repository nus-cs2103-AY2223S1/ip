package skyler;

/**
 * Represents an exception that is thrown when a command is not recognised
 */
public class TaskNotRecognisedException extends SkylerException {
    @Override
    public String getMessage() {
        return "Command not recognised";
    }
}
