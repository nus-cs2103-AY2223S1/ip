package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The MarkCommand that helps the user mark tasks.
 *
 * @author Leong Jia Hao Daniel
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * The constructor for the MarkCommand.
     *
     * @param index The index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command.
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
            throw new InvalidArgumentException(Commands.Mark);
        }
        Task task = taskList.getTask(index);
        task.markAsDone();
        storage.saveFile(taskList);
        return ui.formatMessage("Nice! I've marked this task as done:\n" + task);
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
