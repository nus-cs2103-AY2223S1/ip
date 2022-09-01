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
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index - 1);
        taskList.deleteTasks(index - 1);
        storage.saveTasks(taskList);
        return task + " deleted!";
    }
}
