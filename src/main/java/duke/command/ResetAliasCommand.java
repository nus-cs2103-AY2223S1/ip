package duke.command;

import java.io.IOException;

import duke.inputoutput.DukeIo;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command class that resets set aliases
 */
public class ResetAliasCommand implements Command {
    private static final String OUTRO = "Back to beginning!";

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
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws IOException {
        io.printTask(OUTRO);
        CommandSelector.reset();
    }

}
