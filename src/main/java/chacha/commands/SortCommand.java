package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;

/**
 * Command to list tasks from task list.
 */
public class SortCommand extends Command {

    /** 
     * Sorts all tasks in given task list in chronological order.
     * 
     * @param taskList Task list to sort tasks from.
     * @param ui Ui to handle printing message.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.sort();
        ui.printList(taskList);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
