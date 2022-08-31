package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;

/**
 * Command to execute ending the current session
 *
 * @author Nephelite
 * @version 0.2
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     *
     * @param storage Duke's storage system for tasks
     * @return Duke's response to the execution of the command
     * @throws DukeException for invalid inputs
     * @since 0.2
     */
    @Override
    public String execute(Storage storage) {
        return "Will that be all? Alright then.";
    }
    /**
     * {@inheritDoc}
     *
     * @return true if the command ends the current session. Otherwise, false.
     * @since 0.1
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
