package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The DeleteCommand that helps the user delete tasks.
 *
 * @author Leong Jia Hao Daniel
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * The delete command that delete tasks.
     *
     * @param index The index of the command to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the add task command.
     *
     * @param ui The ui class which handles the user interface.
     * @param storage The storage class which deals with the file.
     * @param taskList The tasklist that stores the tasks.
     * @return The String that duke says.
     * @throws DukeException throws if there is an error.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        if (index >= taskList.numberOfTasks()) {
            throw new InvalidArgumentException(Commands.Delete);
        }
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        storage.saveFile(taskList);
        String message = "Noted. I've removed this task:\n" + task + taskList.tasksLeft();
        return ui.formatMessage(message);
    }

    /**
     * Returns true if the command is an ExitCommand.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
