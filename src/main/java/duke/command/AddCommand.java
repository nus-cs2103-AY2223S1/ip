package duke.command;

import duke.data.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {

    /** Task object to be added to the list */
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the list and displays a message.
     *
     * @param taskList TaskList object containing ArrayList of Task.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addToList(task);
        ui.showAddingTaskMessage(task, taskList.getSize());
    }
}
