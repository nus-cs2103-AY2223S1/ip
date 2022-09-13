package duke.chatbot.commandmanager.commands.exceptions;

/**
 * Exception that is thrown when there is an empty date format provided.
 */
public class EmptyDateFormatException extends InvalidArgumentsException {
    public EmptyDateFormatException() {
        super("You have an empty date format!");
    }
}
