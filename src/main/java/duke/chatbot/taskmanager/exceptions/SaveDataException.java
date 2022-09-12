package duke.chatbot.taskmanager.exceptions;

/**
 * Exception that is thrown when there is a problem when saving data.
 */
public class SaveDataException extends Exception {
    /**
     * Exception that handles a problem when saving data.
     */
    public SaveDataException() {
        super("I seem to have some trouble saving the data...\n");
    }
}
