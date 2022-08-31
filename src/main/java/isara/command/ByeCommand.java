package isara.command;

import java.io.File;

import isara.Ui;
import isara.exception.IsaraException;
import isara.processor.TaskList;
import isara.storage.Storage;

/**
 * Class to represent the command 'bye'.
 *
 * @author Melissa Anastasia Harijanto
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super(true);
    }

    /**
     * Prints the exit lines when the user types 'bye'.
     *
     * @param tasks The list of tasks that the user has inputted.
     */
    @Override
    public String execute(TaskList tasks, File file, Storage storage) throws IsaraException {
        Ui ui = new Ui();
        return ui.exit();
    }
}
