package DukeProgram.Commands;

import Exceptions.InvalidCommandException;

public abstract class Command {

    public abstract boolean execute();

    public abstract Command parse(String commandString) throws InvalidCommandException;
}
