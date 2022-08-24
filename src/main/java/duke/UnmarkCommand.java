package duke;

/**
 * Stores the index of the Task to be unmarked when executed.
 */
public class UnmarkCommand extends Command{
    int indexToUnmark;
    public UnmarkCommand(int number) {
        this.indexToUnmark = number - 1;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(indexToUnmark);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
