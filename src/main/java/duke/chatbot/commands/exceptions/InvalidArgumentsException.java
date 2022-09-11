package duke.chatbot.commands.exceptions;

/**
 * Exception that is thrown when there are invalid arguments to a command
 */
public class InvalidArgumentsException extends Exception {
    public InvalidArgumentsException() {
        super("You placed invalid arguments!\n");
    }
    public InvalidArgumentsException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
