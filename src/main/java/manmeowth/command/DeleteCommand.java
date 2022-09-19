package manmeowth.command;

import manmeowth.exception.ManMeowthException;
import manmeowth.list.TaskList;
import manmeowth.storage.ListLoader;
import manmeowth.task.Task;
import manmeowth.ui.Ui;

/**
 * Represents command to ManMeowth to delete a task from the list.
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
    public String execute(TaskList tasks, Ui ui, ListLoader storage) throws ManMeowthException {
        if (tasks.isValidPosition(position)) {
            Task t = tasks.retrieveRank(position);
            tasks.delete(position);
            storage.deleteTask(t.summary());
            return ui.showDelete(t, tasks.tasksLeft());
        } else {
            return ui.showEmpty();
        }
    }
}
