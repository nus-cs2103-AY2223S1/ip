package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The AddCommand that adds task to the TaskList.
 *
 * @author Leong Jia Hao Daniel
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * The constructor for the AddCommand.
     *
     * @param task The task that is being added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add task command.
     *
     * @param ui The ui class which handles the user interface.
     * @param storage The storage class which deals with the file.
     * @param taskList The tasklist that stores the tasks.
     * @throws DukeException throws if there is an error.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        taskList.addTask(task);
        storage.saveFile(taskList);
        String message = "Got it. I've added this task:\n"
                + "\t" + task
                + taskList.tasksLeft();
        ui.formatMessage(message);
    }

    /**
     * Returns if the command is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
