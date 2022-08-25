package duke.command;

import duke.Storage;

/**
 * Command to execute ending the current session
 * @author Nephelite
 * @version 0.1
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     * @param storage Duke's storage system for tasks
     */
    @Override
    public void execute(Storage storage) {
        System.out.println("Will that be all? Alright then.");
    }
    /**
     * {@inheritDoc}
     * @return true if the command ends the current session. Otherwise, false.
     * @since 0.1
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
