package duke.command;

import duke.ClientList;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command to save task list in save file.
 */
public class SaveTaskListCommand extends Command {
    private static final SaveTaskListCommand SAVE_TASK_LIST_COMMAND = new SaveTaskListCommand();

    /**
     * Returns the save task list command.
     *
     * @return save task list command.
     */
    public static SaveTaskListCommand of() {
        return SAVE_TASK_LIST_COMMAND;
    }

    /**
     * Saves tasks in task list to save file.
     *
     * @param taskList task list.
     * @param clientList client list.
     * @return nothing.
     */
    public String execute(TaskList taskList, ClientList clientList) {
        Storage.saveTaskList(taskList);
        return null;
    }
}
