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
     * Constructs the add command.
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
     * @return The String that Duke will say.
     * @throws DukeException Throws if there is an error.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        taskList.addTask(task);
        storage.saveFile(taskList);
        String message = "Got it. I've added this task:\n"
                + "\t" + task
                + taskList.tasksLeft();
        ui.formatMessage(message);
        return message;
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
