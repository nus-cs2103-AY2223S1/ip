/**
 * Represents command to Duke to mark a task as incomplete.
 *
 * @author WR3nd3
 */
public class UnmarkCommand extends Command {
    /** Position of task to be marked as incomplete in the list */
    private int position;

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
    public void execute(TaskList tasks, Ui ui, ListLoader storage) throws DukeException {
        if (tasks.isValidPosition(position)) {
            Task t = tasks.retrieveRank(position);
            storage.unmarkTask(t.summary());
            tasks.unmark(position);
            ui.showUnmark(t);
        } else {
            ui.showEmpty();
        }
    }
}
