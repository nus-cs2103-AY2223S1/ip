package duke.chatbot.commands.exceptions;

/**
 * Exception that is thrown when there is an empty command.
 */
public class EmptyCommandException extends Exception {
    public EmptyCommandException() {
        super("I don't know what you want if you don't tell me :(\n");
    }
}
