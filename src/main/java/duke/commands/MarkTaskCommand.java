package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class MarkTaskCommand extends Command {

    private final int taskId;

    public MarkTaskCommand(String input) throws DukeException {
        if (!checkValid(input)) throw new DukeException(" ☹ OOPS!!! Please enter task to unmark.");
        this.taskId = Integer.parseInt(input.substring(5)) - 1;
    }

    /**
     * Marks the specified task in the task list.
     *
     * @param taskList the task list which contains the task
     * @param ui the ui to display message after the task is marked
     * @param storage the storage to handle storing of the new task list
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.markTask(this.taskId);
        ui.markTaskMessage(taskList.getTask(this.taskId));
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
