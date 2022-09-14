package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Representation of a command to add task to the taskList.
 */
public class AddCommand extends Command {

    private Task task;

    /*
     * Represents a command to add task into UI and Storage
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /*
     * Attempts to add task to Storage.
     * If successful, Ui shows success.
     * @throws DukeException if task description does not fit specified format.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.addTask(task);
        storage.save(taskList);
        return ui.addSuccess(task, taskList.numOfTask());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            AddCommand x = (AddCommand) obj;
            if (this.task == null || x.task == null) {
                return false;
            }
            return this.task.equals(x.task);
        }

        return false;
    }
}
