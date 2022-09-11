package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.ui.Ui;

/**
 * ExitCommand class handles the command to end the program.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     * Quits the program.
     */
    private static final String GOODBYE_MESSAGE = "I loved talking to you ･ω･\n" + "Hope to see you again!";
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return GOODBYE_MESSAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
