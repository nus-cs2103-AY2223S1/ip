package kirby.commands;

import kirby.TaskList;
import kirby.Ui;
import kirby.Storage;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        String output = ("I loved talking to you ･ω･\n" + "Hope to see you again!");
        System.out.println(output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
