package duke.chatbot.commandmanager.commands.exceptions;

/**
 * Exception that is thrown when there is an invalid event time.
 */
public class InvalidEventException extends InvalidArgumentsException {
    public InvalidEventException(String dateFormat) {
        super("You have an invalid event time!\nEvent times should be in the format: " + dateFormat + "\n");
    }
}
