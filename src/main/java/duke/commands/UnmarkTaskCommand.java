package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class UnmarkTaskCommand extends Command {

    private final int taskId;

    public UnmarkTaskCommand(String input) throws DukeException {
        if (!checkValid(input)) throw new DukeException(" ☹ OOPS!!! Please enter task to unmark.");
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
