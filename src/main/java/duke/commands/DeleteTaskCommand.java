package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class DeleteTaskCommand extends Command {

    private final int taskId;

    public DeleteTaskCommand(String input) throws DukeException {
        if (!checkValid(input)) { throw new DukeException(" ☹ OOPS!!! Please enter task to delete."); }
        this.taskId = Integer.parseInt(input.substring(7)) - 1;
    }

    /**
     * Deletes a specific task in the task list.
     *
     * @param taskList the task list from which the task is to be deleted
     * @param ui the ui to display message after the task is deleted
     * @param storage the storage to handle storing of the new task list
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.deleteTaskMessage(taskList.getTask(taskId), taskList.getSize());
        taskList.deleteTask(taskId);
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
