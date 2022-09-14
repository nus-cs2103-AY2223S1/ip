package duke.command;


import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents a command that is used to delete a task within the task list, save the new tasks
 * and print out the task that was deleted.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor for the DeleteCommand
     *
     * @param index
     */
    public DeleteCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    /**
     * Execute method that is used to delete a task from the tasklist, save the new tasks
     * and print out the tasks that was delete through tasklist, ui and storage.
     *
    * @param taskList
     * @param archiveTaskList
     * @param storage
     * @param archiveStorage
     * @param ui
     */
    @Override
    public String execute(TaskList taskList, TaskList archiveTaskList, Storage storage,
                          Storage archiveStorage, Ui ui) {
        int normalisedIndex = index - 1;
        Task task = taskList.getTask(normalisedIndex);
        archiveTaskList.addTasks(task);
        archiveStorage.saveTasks(archiveTaskList);
        taskList.deleteTasks(normalisedIndex);
        storage.saveTasks(taskList);
        return task + " deleted!";
    }
}
