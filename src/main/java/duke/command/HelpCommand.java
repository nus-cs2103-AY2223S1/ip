package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command that shows a help message to the user
 */
public class HelpCommand extends Command {

    /**
     *
     */
    public HelpCommand() {
        super();
    }


    /**
     * Executes the command to print a help message to the user
     * @param tasks
     * @param storage
     * @param ui
     * @return
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.showHelpMessage();
    }
}
