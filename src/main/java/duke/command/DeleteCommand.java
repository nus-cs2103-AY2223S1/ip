package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UnmarkCommand is a Command that deletes a Task.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class DeleteCommand extends Command {
    private int idx;

    /**
     * Constructor for DeleteCommand.
     *
     * @param idx Index of Task to be deleted.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Returns the response from Duke after deleting the Task with the given index.
     *
     * @param tasks tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     * @return The response from Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (idx < 0 || idx >= tasks.getSize()) {
            throw new DukeException("The index provided is not within the list.");
        }
        String task = tasks.deleteTask(idx);
        storage.save(tasks.saveTasks());
        return ui.showDelete(task, tasks.getSize());
    }
}
