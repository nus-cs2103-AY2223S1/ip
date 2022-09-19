package manmeowth.command;

import manmeowth.exception.ManMeowthException;
import manmeowth.list.TaskList;
import manmeowth.storage.ListLoader;
import manmeowth.task.Task;
import manmeowth.ui.Ui;

/**
 * Represents command to ManMeowth to mark a task as complete.
 *
 * @author WR3nd3
 */
public class MarkCommand extends Command {
    /** Position of task to be marked as complete in the list */
    private final int position;

    /**
     * Constructs MarkCommand for a given task.
     *
     * @param position Integer representing position of task to be marked as complete in the list.
     */
    public MarkCommand(int position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, ListLoader storage) throws ManMeowthException {
        if (tasks.isValidPosition(position)) {
            Task t = tasks.retrieveRank(position);
            storage.markTask(t.summary());
            tasks.mark(position);
            return ui.showMark(t);
        } else {
            return ui.showEmpty();
        }
    }
}
