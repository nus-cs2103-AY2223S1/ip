package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;
import duke.util.DukeIo;
import duke.util.Storage;
import duke.util.TaskList;

public class InvalidCommand implements Command {

    @Override
    public boolean isExit() {
        return false;
    }
    
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws DukeException, IOException {
        throw new UnknownCommandException();
    }
    
}
