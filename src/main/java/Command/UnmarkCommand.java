package Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.Storage;
import Duke.DukeException;
import Duke.Constants;
import Tasks.Task;

/**
 * Marks a specific task as not done
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructor whhich creates an unmark command with the index of task given by the user
     * @param index
     */
    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Marks the specific tasks as not done.
     * If the tasks is not in the tasklist, it will throw an error to warn the user to
     * input the commands correctly.
     * It then saves the new tasklist into the file.
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @throws DukeException which handles the error of invalid index input by the user
     * @return string that will be printed in the UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index > taskList.getSize()) {
            throw new DukeException(Constants.INVALID_INDEX);
        }
        Task task = taskList.unmarkTask(index);
        storage.writeFile(taskList.tasksToString());
        return ui.printUnmarkTask(task);
    }
}
