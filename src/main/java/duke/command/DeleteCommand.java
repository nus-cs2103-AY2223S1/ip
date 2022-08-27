package duke.command;

import duke.exception.DukeException;
import duke.list.TaskList;
import duke.storage.ListLoader;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents command to Duke to delete a task from the list.
 *
 * @author WR3nd3
 */
public class DeleteCommand extends Command {
    /** Position of task to be deleted from the list */
    private final int position;

    /**
     * Constructs DeleteCommand for a given task.
     *
     * @param position Integer representing position of task to be deleted in the list.
     */
    public DeleteCommand(int position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, ListLoader storage) throws DukeException {
        if (tasks.isValidPosition(position)) {
            Task t = tasks.retrieveRank(position);
            tasks.delete(position);
            storage.deleteTask(t.summary());
            ui.showDelete(t, tasks.tasksLeft());
        } else {
            ui.showEmpty();
        }
    }
}
