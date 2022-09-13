package duke.chatbot.commandmanager.commands.exceptions;

/**
 * Exception that is thrown when the index is out of bounds of the task list
 */
public class NoSuchIndexException extends InvalidArgumentsException {
    public NoSuchIndexException() {
        super("There is no such task!!\n");
    }
}
