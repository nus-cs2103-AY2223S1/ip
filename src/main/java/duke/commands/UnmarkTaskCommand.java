package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;
/**
 * UnmarkTaskCommand implements method for unmarking a task in the task list.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class UnmarkTaskCommand extends Command {

    private final int taskId;

    /**
     * Creates new UnmarkTaskCommand object.
     *
     * @param input the input string from the user
     * @throws DukeException to handle if the input string is invalid.
     */
    public UnmarkTaskCommand(String input) throws DukeException {
        if (!checkValid(input)) {
            throw new DukeException(" ☹ OOPS!!! Please enter task to unmark.");
        }
        this.taskId = Integer.parseInt(input.substring(7)) - 1;
    }

    /**
     * Unmarks the specified task in the task list.
     *
     * @param taskList the task list which contains the task
     * @param ui the ui to display message after the task is unmarked
     * @param storage the storage to handle storing of the new task list
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.unmarkTask(this.taskId);
        ui.unmarkTaskMessage(taskList.getTask(this.taskId));
        storage.store(taskList);
    }

    /**
     * Prevents the program from terminating in Duke.run().
     *
     * @return False as this is not the 'exit' command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
