package duke.command;

import java.io.IOException;

import duke.exceptions.UnknownCommandException;
import duke.inputoutput.DukeIo;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command class that resets set aliases
 */
public class ResetAliasCommand extends NoParamCommand {

    private static final String OUTRO = "Back to beginning!";

    public ResetAliasCommand(ParsedData data) {
        super(data);
    }

    /**
     * Returns true when asked if program should exit.
     *
     * @return boolean
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc} Resets all added aliases.
     *
     * @throws IOException raised if an error occured when saving
     * @throws UnknownCommandException when extra parameters is included
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws IOException, UnknownCommandException {
        checkSingleArgumentGuard();
        io.printTask(OUTRO);
        CommandSelector.reset();
    }

}
