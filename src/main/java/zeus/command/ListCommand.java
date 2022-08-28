package zeus.command;

import zeus.exception.ZeusException;
import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;

/**
 * Class that represents the Command to list the tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to get the list.
     *
     * @param taskList List of tasks
     * @param ui The Ui
     * @param storage The Storage
     * @throws ZeusException If user input is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList.getTaskList());
    }
}
