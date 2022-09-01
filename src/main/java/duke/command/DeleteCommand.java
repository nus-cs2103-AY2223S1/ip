package duke.command;

import duke.FileStorage;
import duke.Ui;
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
     * Deletes a task from the taskList, saves to file
     * and returns the corresponding message to the GUI.
     *
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The ui of Duke.
     * @return The string meant for the GUI.
     */
    @Override
    public String execute(TaskList list, FileStorage storage, Ui ui) {
        Task task = list.retrieveTask(index);
        list.deleteTask(index);
        storage.writeToFile(list.getList());
        return ui.getDeletedTaskMessage(list, task);
    }
}
