package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to display help
 */
public class HelpCommand extends Command {
    /**
     * To execute the {@code HelpCommand}
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the current {@code Ui}
     * @param storage the current {@code Storage}
     * @return a string to be printed onto the dialog box
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.helpTask();
    }
}
