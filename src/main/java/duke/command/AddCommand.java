package duke.command;

import duke.data.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/** Command that adds tasks into the list */
public class AddCommand extends Command {

    /** Task object to be added to the list */
    private Task task;

    /**
     * Initialises the object and set field task.
     *
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }


    /**
     * Adds the task to the list and displays a message.
     *
     * @param taskList TaskList object containing ArrayList of Task.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return String.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addToList(task);
        return ui.showAddingTaskMessage(task, taskList.getSize());
    }
}
