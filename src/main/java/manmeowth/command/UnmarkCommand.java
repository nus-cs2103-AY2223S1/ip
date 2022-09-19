package manmeowth.command;

import manmeowth.exception.ManMeowthException;
import manmeowth.list.TaskList;
import manmeowth.storage.ListLoader;
import manmeowth.task.Task;
import manmeowth.ui.Ui;

/**
 * Represents command to ManMeowth to mark a task as incomplete.
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
    public String execute(TaskList tasks, Ui ui, ListLoader storage) throws ManMeowthException {
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
