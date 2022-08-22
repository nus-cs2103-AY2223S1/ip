package duke.command;

import duke.DukeException;

public class InvalidCommand extends Command {
    @Override
    public void execute() throws DukeException {
        throw new DukeException("Unknown command. Please try again.");
    }
}
