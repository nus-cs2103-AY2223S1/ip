package duke.command;

import java.io.IOException;

import duke.exceptions.UnknownCommandException;
import duke.util.DukeIo;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command to indicate an invalid command.
 */
public class InvalidCommand implements Command {

    /**
     * {@inheritDoc}
     * Does not end program
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     * Throws invalid command exception
     * 
     * @throws UnknownCommandException Throw invalid command
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws UnknownCommandException, IOException {
        throw new UnknownCommandException();
    }

}
