package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Stores the index of the Task to be unmarked when executed.
 */
public class UnmarkCommand extends Command {
    private int indexToUnmark;

    /**
     * @param number 1-indexed task to be removed
     */
    public UnmarkCommand(int number) {
        assert number >= 1 : "Number needs to be positive";
        this.indexToUnmark = number - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
