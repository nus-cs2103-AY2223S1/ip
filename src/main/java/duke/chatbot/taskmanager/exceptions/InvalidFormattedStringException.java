package duke.chatbot.taskmanager.exceptions;

/**
 * Exception that is thrown when there is an invalid formatted string present in the save file.
 */
public class InvalidFormattedStringException extends IndexOutOfBoundsException {
    /**
     * Exception that handles invalid formatted strings present in the save file.
     */
    public InvalidFormattedStringException() {
        super("There is an invalid formatted string in the save file!\n");
    }
}
