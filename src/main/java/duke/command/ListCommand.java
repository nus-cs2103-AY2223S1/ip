package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/** Command that helps to list the tasks in the list */
public class ListCommand extends Command {

    /**
     * Displays a list of tasks.
     *
     * @param taskList TaskList object containing ArrayList of Task.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return String.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showListMessage(taskList.enumerateList());
    }
}
