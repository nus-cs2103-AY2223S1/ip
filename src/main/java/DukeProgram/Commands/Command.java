package dukeprogram.commands;

import dukeprogram.UiMessage;
import exceptions.InvalidCommandException;

public abstract class Command {

    public abstract boolean execute();

    public Command parse(String commandString) throws InvalidCommandException {
        throw new InvalidCommandException(this, commandString,
                new UiMessage(String.format("I cannot understand %s", commandString))
        );
    }
}
