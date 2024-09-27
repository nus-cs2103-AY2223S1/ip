package command;

import mykoba.Storage;
import mykoba.TaskList;
import mykoba.Ui;

/**
 * This class encapsulates a command asking Koba to print the help message.
 */
public class HelpCommand extends Command {

    /**
     * Brings out the help page.
     *
     * @param tasklist The TaskList instance for the task manager.
     * @param ui       The Ui instance for the task manager.
     * @param storage  The Storage instance for the task manager.
     * @return a String showing all the valid commands.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return ui.getHelpPage();
    }
}