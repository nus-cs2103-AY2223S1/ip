package duke.command;

import duke.exception.DukeException;
import duke.list.TaskList;
import duke.storage.ListLoader;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents command to Duke to mark a task as incomplete.
 *
 * @author WR3nd3
 */
public class UnmarkCommand extends Command {
    /** Position of task to be marked as incomplete in the list */
    private final int position;

    /**
     * Constructs UnmarkCommand for a given task.
     *
     * @param position Integer representing position of task to be marked as incomplete in the list.
     */
    public UnmarkCommand(int position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, ListLoader storage) throws DukeException {
        if (tasks.isValidPosition(position)) {
            Task t = tasks.retrieveRank(position);
            storage.unmarkTask(t.summary());
            tasks.unmark(position);
            return ui.showUnmark(t);
        } else {
            return ui.showEmpty();
        }
    }
}
