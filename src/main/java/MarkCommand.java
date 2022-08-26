/**
 * Represents command to Duke to mark a task as complete.
 *
 * @author WR3nd3
 */
public class MarkCommand extends Command {
    /** Position of task to be marked as complete in the list */
    private int position;

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
    public void execute(TaskList tasks, Ui ui, ListLoader storage) throws DukeException {
        if (tasks.isValidPosition(position)) {
            Task t = tasks.retrieveRank(position);
            storage.markTask(t.summary());
            tasks.mark(position);
            ui.showMark(t);
        } else {
            ui.showEmpty();
        }
    }
}
