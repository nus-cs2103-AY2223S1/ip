package exceptions;

import dukeprogram.UiMessage;
import dukeprogram.commands.Command;

/**
 * InvalidCommandException is thrown when no such command is valid
 */
public class InvalidCommandException extends Exception {
    private final UiMessage uiMessage;

    /**
     * Creates a new invalid command exception
     * @param presentCommand the current command context
     * @param givenCommandString the string given to be parsed as a command
     * @param message the ui message to show to the user
     */
    public InvalidCommandException(Command presentCommand, String givenCommandString, UiMessage message) {
        super(String.format(
                "%s was unable to interpret the command \"%s\"",
                presentCommand.toString(),
                givenCommandString)
        );
        uiMessage = message;
    }

    /**
     * Creates a new invalid command exception
     * @param givenCommandString the string given to be parsed as a command
     * @param message the ui message to show to the user
     */
    public InvalidCommandException(String givenCommandString, UiMessage message) {
        super(String.format(
                "Unable to interpret the command \"%s\"",
                givenCommandString)
        );
        uiMessage = message;
    }

    /**
     * Creates a new invalid command exception
     * @param uiMessage the ui message to show to the user
     */
    public InvalidCommandException(UiMessage uiMessage) {
        super("Invalid command given");
        this.uiMessage = uiMessage;
    }

    public UiMessage getUiMessage() {
        return uiMessage;
    }
}
