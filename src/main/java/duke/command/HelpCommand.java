package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Shows a help message to the user
 */
public class HelpCommand extends Command {

    /**
     * Initialises a {@link HelpCommand} instance
     */
    public HelpCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.showHelpMessage();
    }
}
