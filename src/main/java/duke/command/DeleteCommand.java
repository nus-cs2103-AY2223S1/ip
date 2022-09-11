package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.exception.DukeOutOfBoundsException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DeleteCommand is a Command that handles delete.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */

public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index Index of the item in TaskList.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Outputs the task being deleted.
     *
     * @param tasks A TaskList containing the Tasks.
     * @param ui The Ui which handles interactions with the user.
     * @param storage The Storage which handles loading and saving data from the file.
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeOutOfBoundsException(1, tasks.getSize());
        }
        Task deletedTask = tasks.deleteTask(index);
        storage.save(tasks);
        return ui.displayDelete(deletedTask);
    }
}
