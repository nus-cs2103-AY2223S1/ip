package Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.Storage;
import Duke.DukeException;
import Duke.Constants;
import Tasks.Task;

/**
 * Marks a specific task as done
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor that creates a new mark command with the index of tasks given
     *
     * @param index
     */
    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Marks a specific tasks as done. If the tasks is unknown,
     * the program will throw an error to warn the user to input
     * the commands correctly.
     * After doing the command, it will save the new tasklist into the file.
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @throws DukeException which handles the error of invalid index input by the user
     * @return string that will be printed in the UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > taskList.getSize()) {
            throw new DukeException(Constants.INVALID_INDEX);
        }
        Task task = taskList.markTask(index);
        storage.writeFile(taskList.tasksToString());
        return ui.printMarkTask(task);
    }
}
