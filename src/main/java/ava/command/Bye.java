package ava.command;

import ava.Ui;
import ava.util.Storage;
import ava.util.TaskList;

/**
 * Class to represent "Bye" command.
 */
public class Bye extends Command {
    /**
     * Cleans up the UI.
     *
     * @param tasks TaskList.
     * @param storage Class to write/read commands from file.
     * @param ui Class to print the ui.
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printExit();
    }

    /**
     * Tests if a command is exit.
     *
     * @return True.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
