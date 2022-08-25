/**
 * Marks a specific task as done
 */
package Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.Storage;
import Duke.DukeException;
import Duke.Constants;
import Tasks.Task;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int idx) {
        super();
        this.index = idx;
    }

    /**
     * Marks a specific tasks as done. If the tasks is unknown,
     * the program will throw an error to warn the user to input
     * the commands correctly.
     * After doing the command, it will save the new tasklist into the file.
     *
     * @param t which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @throws DukeException which handles the error of invalid index input by the user
     */
    @Override
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > t.getSize()) {
            throw new DukeException(Constants.INVALID_INDEX);
        }
        Task tsk = t.markTask(index);
        storage.writeFile(t.tasksToString());
        ui.printMarkTask(tsk);
    }
}
