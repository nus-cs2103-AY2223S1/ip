package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeOutOfBoundsException;
import duke.task.Task;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

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
     * @throws DukeException The exception thrown when an action is unauthorized by Duke.
     * @throws IOException The exception thrown when accessing files is incorrect.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeOutOfBoundsException(1, tasks.getSize());
        }
        Task deletedTask = tasks.deleteTask(index);
        storage.save(tasks);
        ui.displayDelete(deletedTask);
    }
}
