package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Unmarks the specified task from the <Code>TaskList</Code>.
     *
     * @param tasks The <code>TaskList</code> object containing all stored tasks.
     * @param ui The <code>Ui</code> object
     * @param storage The database object.
     * @throws DukeException if index number provided is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (idx <= 0 || idx > tasks.getSize()) {
            throw new DukeException("Invalid task number, please check your task number again.");
        }
        Task task = tasks.getTask(idx - 1);
        task.unmark();
        ui.sendMessage("Unmarked '" + task.getDescription() + "'.");
        tasks.updateStorage();
    }
}
