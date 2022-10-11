package rattus.chatbot.data.exception;

/**
 * An exception that is thrown when an invalid argument is passed to the UI.
 *
 * @author jq1836
 */
public class InvalidInputException extends Exception {
    public InvalidInputException() {}

    public InvalidInputException(String message) {
        super(message);
    }
}
