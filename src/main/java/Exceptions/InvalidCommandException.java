package Exceptions;

import DukeProgram.Commands.Command;
import DukeProgram.UiMessage;

public class InvalidCommandException extends Exception {
    private final UiMessage uiMessage;

    public InvalidCommandException(Command presentCommand, String givenCommandString, UiMessage message) {
        super(String.format(
                "%s was unable to interpret the command \"%s\"",
                presentCommand.toString(),
                givenCommandString)
        );
        uiMessage = message;
    }

    public InvalidCommandException(String givenCommandString, UiMessage message) {
        super(String.format(
                "Unable to interpret the command \"%s\"",
                givenCommandString)
        );
        uiMessage = message;
    }

    public InvalidCommandException(UiMessage uiMessage) {
        super("Invalid command given");
        this.uiMessage = uiMessage;
    }

    public UiMessage getUiMessage() {
        return uiMessage;
    }
}
