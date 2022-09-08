package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;

/**
 * Command to list tasks from task list.
 */
public class ListCommand extends Command {

    /** 
     * Lists all tasks in given task list.
     * 
     * @param taskList Task list to print tasks from.
     * @param ui Ui to handle printing message.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
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
