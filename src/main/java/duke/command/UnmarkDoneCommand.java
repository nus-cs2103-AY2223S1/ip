package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Command to unmark a done task.
 */
public class UnmarkDoneCommand extends Command {
    private int index;

    /**
     * Constructor for <code>UnmarkDoneCommand</code>
     * @param index
     */
    public UnmarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String getResponse(TaskList tasks, Storage storage) throws DukeException {
        tasks.unmarkDone(index);
        return "Baa! I have unmarked this task\n"
                + tasks.getTask(index).toString();
    }

    /**
     * Check if the command exit duke.
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

