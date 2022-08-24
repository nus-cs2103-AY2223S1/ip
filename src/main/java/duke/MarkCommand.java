package duke;

/**
 * Stores the index of the task to be marked when executed by MarkCommand.
 */
public class MarkCommand extends Command{
    int indexToMark;

    /**
     * @param number 1 based indexing for the Task to be marked.
     */
    MarkCommand(int number) {
        this.indexToMark = number - 1;
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(indexToMark);
    }

    /**
     * Does not terminate the App.
     * @return false.
     */
    @Override
    boolean isExit() {
        return false;
    }
}
