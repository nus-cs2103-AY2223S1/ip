package duke.chatbot.commands.exceptions;

/**
 * Exception that is thrown when there is an invalid deadline.
 */
public class InvalidDeadlineException extends InvalidArgumentsException {
    public InvalidDeadlineException(String dateFormat) {
        super("You have an invalid deadline!\nDeadlines should be in the format: " + dateFormat + "\n");
    }
}
