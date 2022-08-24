package duke.command;

import duke.DukeException;
import duke.Storage;

/**
 * Command to execute ending the current session
 * @author Nephelite
 * @version 0.1
 */
public class ExitCommand extends Command {
    @Override
    public void execute(Storage storage) throws DukeException {
        System.out.println("Will that be all? Alright then.");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
