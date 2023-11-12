package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.util.DukeException;
import duke.util.Ui;

/**
 * Executes the command to update the tasks
 *
 * @author Jicson Toh
 */
public class TaskUpdateCommand extends Command {
    private final String action;

    /**
     * Creates a task update object
     *
     * @param action
     */
    public TaskUpdateCommand(String action) {
        this.action = action;
    }

    /**
     * Executes the command input.
     *
     * @param tasks   amends task list if any.
     * @param ui      ui to output feedback.
     * @param storage make changes to storage if any.
     * @return output of the action.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(action.substring(0, 1)) - 1;
            int colon = action.substring(2).indexOf(':');
            String type = action.substring(2, colon + 2);
            String changes = action.substring(colon + 3).strip();
            tasks.renameTask(index, changes);
            return ui.showUpdatedTask(changes, index + 1);
        } catch (Exception e) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
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
