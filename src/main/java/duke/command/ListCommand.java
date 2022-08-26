package duke.command;

import duke.main.Ui;
import duke.main.TaskList;
import duke.main.Storage;

/**
 * Represents a command that is used to list out the tasks within a tasklist.
 */
public class ListCommand extends Command {

    /**
     * Execute method that is used to list all tasks in the tasklist through
     * tasklist, ui and storage.
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
       ui.list(taskList);
    }
}
