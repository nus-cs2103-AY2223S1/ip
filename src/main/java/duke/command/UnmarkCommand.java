package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents a command that is used to unmark a task in the tasklist, save the task
 * and print out the task that was unmarked.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructor for UnmarkCommand
     *
     * @param index
     */
    public UnmarkCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    /**
     * Returns a string after method unmark a task in a tasklist, save the task
     * and print out the task that was unmarked through tasklist, ui and storage.
     *
     * @param taskList
     * @param archiveTaskList
     * @param storage
     * @param archiveStorage
     * @param ui
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     * @throws NumberFormatException
     */
    @Override
    public String execute(TaskList taskList, TaskList archiveTaskList, Storage storage,
                          Storage archiveStorage, Ui ui) throws NullPointerException,
            IndexOutOfBoundsException, NumberFormatException {
        int normalisedIndex = index - 1;
        Task task = taskList.getTask(normalisedIndex);
        task.setUndone();
        storage.saveTasks(taskList);
        String response = task + " unmarked";
        return response;
    }
}
