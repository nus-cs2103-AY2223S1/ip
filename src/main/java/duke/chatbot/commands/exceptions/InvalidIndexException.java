package duke.chatbot.commands.exceptions;

/**
 * Exception that is thrown when there is no number after a command that requires one
 */
public class InvalidIndexException extends InvalidArgumentsException {
    public InvalidIndexException() {
        super("You need to put a number after your command!\n");
    }
}
