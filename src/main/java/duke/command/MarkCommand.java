package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents a command that is used to mark a task in the tasklist, save the task
 * and print out the task that was marked.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for MarkCommand
     *
     * @param index
     */
    public MarkCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    /**
     * Return a string after method marks a task in a tasklist, save the task
     * and print out the task that was marked through tasklist, ui and storage.
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
        task.setDone();
        storage.saveTasks(taskList);
        String response = task + " marked";
        return response;
    }
}
