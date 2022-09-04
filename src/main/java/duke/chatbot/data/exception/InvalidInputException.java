package duke.chatbot.data.exception;

import duke.chatbot.command.Command;
import duke.chatbot.command.InvalidInputCommand;

/**
 * An exception that is thrown when an invalid argument is
 * passed to the UI.
 *
 * @author jq1836
 */
public class InvalidInputException extends Exception {
    public InvalidInputException() {}

    public InvalidInputException(String message) {
        super(message);
    }

    /**
     * Returns an instance of {@link InvalidInputCommand} with an error message.
     * @return An instance of InvalidInputCommand with an error message
     */
    public Command getCommand() {
        return new InvalidInputCommand(getMessage());
    }
}
