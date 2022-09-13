package duke.chatbot.commandmanager.commands.exceptions;

/**
 * Exception that is thrown when there is an invalid command.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Sorry, I don't understand what you mean :(\n");
    }
}
