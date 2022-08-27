package duke.command;

import duke.FileStorage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command used to delete a task from the taskList.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int index;
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Deletes a task from the taskList, saves to file.
     *     and prints out the corresponding message to the user.
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     */
    @Override
    public String execute(TaskList list, FileStorage storage) {
        Task task = list.retrieveTask(index);
        list.deleteTask(index);
        storage.writeToFile(list.getList());
        return String.format("Noted. I've removed this task:\n  %s"
                + "\nNow you have %d tasks in the list.", task , list.getListSize());
    }
}
