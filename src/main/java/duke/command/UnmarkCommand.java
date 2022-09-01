package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Stores the index of the Task to be unmarked when executed.
 */
public class UnmarkCommand extends Command {
    private int indexToUnmark;

    public UnmarkCommand(int number) {
        this.indexToUnmark = number - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Task unmarked: " + tasks.unmark(indexToUnmark);
    }

    /**
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
