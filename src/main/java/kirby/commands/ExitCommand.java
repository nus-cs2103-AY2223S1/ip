package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.Ui;
import kirby.exceptions.KirbyMissingArgumentException;

/**
 * ExitCommand class handles the command to end the program.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     * Quits the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        String output = ("I loved talking to you ･ω･\n" + "Hope to see you again!");
        return output;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
