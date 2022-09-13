package duke.chatbot.taskmanager.exceptions;

/**
 * Exception that is thrown when there is a problem when loading data.
 */
public class LoadDataException extends Exception {
    /**
     * Exception that handles a problem when loading data.
     */
    public LoadDataException(String exceptionMessage) {
        super("I am having some trouble loading the data!\n" + exceptionMessage);
    }
}
