package duke.command;

import duke.exceptions.UnknownCommandException;
import duke.inputoutput.DukeIo;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command to list out all the current tasks.
 */
public class ListCommand extends NoParamCommand {

    public ListCommand(ParsedData data) {
        super(data);
    }

    /**
     * {@inheritDoc} List command does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out all the current tasks added.
     *
     * @throws UnknownCommandException when extra parameters is included
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws UnknownCommandException {
        checkSingleArgumentGuard();
        io.printNumberedList(tasks.getTasks());
    }

}
