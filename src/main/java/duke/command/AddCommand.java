package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

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
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        taskList.addTask(task);
        storage.save(taskList);
        ui.addSuccess(task, taskList.numOfTask());
    }
}
