/**
 * Deletes a specific tasks in the list
 */
package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int idx) {
        super();
        this.index = idx;
    }

    /**
     * Deletes a task from the tasklist and save the new
     * tasklist into the file.
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task deleted = taskList.deleteTask(index);
        storage.writeFile(taskList.tasksToString());
        ui.printDeleteTask(deleted, taskList.getSize());
    }
}
