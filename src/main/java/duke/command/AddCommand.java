package duke.command;

import duke.data.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * AddCommand class
 */
public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Add task to task list and disk storage
     *
     * @param storage Disk storage
     * @param tasks   Task list
     * @param ui      Ui to display to users
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.addNewTask(task);
        storage.store(tasks);
        return "Got it. I've added this task: \n "
                        + task
                        + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

}
