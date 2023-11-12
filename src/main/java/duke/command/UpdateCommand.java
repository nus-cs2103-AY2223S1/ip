package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.util.DukeException;
import duke.util.Ui;

/**
 * Executes the command to choose the task to update
 *
 * @author Jicson Toh
 */
public class UpdateCommand extends Command {

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
        String result = "You have entered update mode. "
                + "Please use the following format to update your task.\n"
                + "(choose index of task):(rename):(changes to made)\n"
                + "E.g. 1: rename: borrow book\n";
        return result + ui.getList(tasks.getList());
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
