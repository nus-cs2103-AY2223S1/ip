package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to delete a task from task list.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a delete command of specified index.
     *
     * @param index The 1-indexed position of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the specified task list.
     *
     * @param storage Storage handling the file IO.
     * @param taskList A list of tasks.
     * @param ui A ui to handle printing output.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            String output = "";
            output += ui.printDeleteTask(taskList.deleteTask(index - 1)) + '\n';
            output += ui.printSizeOfList(taskList.size());
            storage.save(taskList.getTasks());
            return output;
        } catch (DukeException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
