/**
 * The MarkCommand class represents a command
 * that marks a task in Duke as done.
 */
public class MarkCommand extends Command {
    /** The number of the task to be deleted. */
    private final int n;

    /**
     * Constructs a new MarkCommand.
     *
     * @param n The number (as displayed in the full list) of the task to be marked as done.
     */
    public MarkCommand(int n) {
        this.n = n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.mark(n);
        storage.writeToFile(tasks);
        ui.showMarked(task);
    }
}
