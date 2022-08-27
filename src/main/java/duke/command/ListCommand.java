package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.util.Ui;

/**
 * Executes the command to show the list of tasks
 * @author Jicson Toh
 */
public class ListCommand extends Command {

    /**
     * Executes the command input.
     *
     * @param task    amends task list if any.
     * @param ui      ui to output feedback.
     * @param storage make changes to storage if any.
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.getList(task.getList());
    }

    /**
     * Returns true if exiting program.
     *
     * @return false if still running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
