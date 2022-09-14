package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

/**
 * Deletes a specific tasks in the list
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor that creates a new delete command with the given index
     *
     * @param index
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Deletes a task from the task list and save the new
     * task list into the file.
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @return string that will be printed in the UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task deleted = taskList.deleteTask(index);
        storage.writeFile(taskList.tasksToString());
        return ui.printDeleteTask(deleted, taskList.getSize());
    }
}
