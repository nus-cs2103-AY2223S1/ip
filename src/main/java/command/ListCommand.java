package command;

import myduke.Storage;
import myduke.TaskList;
import myduke.Ui;

public class ListCommand extends Command {
    /**
     * Method that prints all the Tasks currently in the TaskList.
     *
     * @param tasklist The TaskList instance for the task manager.
     * @param ui       The Ui instance for the task manager.
     * @param storage  The Storage instance for the task manager.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return ui.listtoString(tasklist);
    }
}
