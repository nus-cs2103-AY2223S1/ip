package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * MarkTaskCommand implements method for marking a task in the task list.
 *
 * @author Isaac Li Haoyang
 * @version v0.2
 */
public class MarkTaskCommand extends Command {

    private final int taskId;

    /**
     * Creates new MarkTaskCommand object.
     *
     * @param input the input string from the user
     * @throws DukeException to handle if the input string is invalid.
     */
    public MarkTaskCommand(String input) throws DukeException {
        if (!checkValid(input)) {
            throw new DukeException(" ☹ OOPS!!! Please enter task to unmark.");
        }
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
        this.response = ui.markTaskMessage(taskList.getTask(this.taskId));
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
