package DukeProgram.Commands;

import Exceptions.InvalidCommandException;

public class ExitCommand extends Command {
    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        throw new InvalidCommandException(this, commandString);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
