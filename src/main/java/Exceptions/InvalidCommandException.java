package Exceptions;

import DukeProgram.Commands.Command;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(Command presentCommand, String givenCommandString) {
        super(String.format(
                "%s was unable to interpret the command \"%s\"",
                presentCommand.toString(),
                givenCommandString)
        );
    }

    public InvalidCommandException(String givenCommandString) {
        super(String.format(
                "Unable to interpret the command \"%s\"",
                givenCommandString)
        );
    }

    public InvalidCommandException() {
        super("Invalid command given");
    }
}
