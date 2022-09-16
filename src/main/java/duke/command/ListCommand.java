package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to list out the tasks
 */
public class ListCommand extends Command {

    /**
     * To execute the {@code ListCommand}
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the current {@code Ui}
     * @param storage the current {@code Storage}
     * @return a string to be printed onto the dialog box
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listTask(tasks);
    }
}
