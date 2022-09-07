package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;


/**
 * Provides a list of commands user may try.
 * Does not elaborate on their usage.
 */
public class HelpCommand extends Command {
    private String[] slicedUserCommands;

    public HelpCommand(String[] slicedUserInput) {
        this.slicedUserCommands = slicedUserInput;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        ui.showHelpMessage();
    }
}
