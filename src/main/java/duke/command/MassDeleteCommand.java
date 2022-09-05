package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The MassDeleteCommand that helps the user delete tasks.
 *
 * @author Leong Jia Hao Daniel
 */
public class MassDeleteCommand extends Command {

    /**
     * Constructs the mass delete command.
     */
    public MassDeleteCommand() {

    }

    /**
     * Executes the mass delete task command.
     *
     * @param ui The ui class which handles the user interface.
     * @param storage The storage class which deals with the file.
     * @param taskList The tasklist that stores the tasks.
     * @return The String that duke says.
     * @throws DukeException Throws if there is an error.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        String tasks = taskList.clearCompletedTasks();
        String message = "Noted. I've removed these tasks:\n" + tasks + taskList.tasksLeft();
        storage.saveFile(taskList);
        return ui.formatMessage(message);
    }

    /**
     * Returns true if the command is an ExitCommand.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
