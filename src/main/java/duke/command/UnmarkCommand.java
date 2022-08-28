package duke.command;

import duke.exception.DukeException;
import duke.gui.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;


/**
 * Represents the command that is executed when the user inputs unmark.
 *
 * @author njxue
 * @version v0.1
 */
public class UnmarkCommand extends Command {
    /** The index of the task in the TaskList to be unmarked. */
    private int taskIndex;

    /**
     * Creates an UnmarkCommand.
     *
     * @param taskIndex The index of the task in the TaskList to be unmarked as done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Returns the format of the unmark command.
     *
     * @return The format of the unmark command.
     */
    public static String getFormat() {
        return "unmark <Integer>";
    }

    /**
     * Executes the unmark command. Deletes the specific task in the TaskList.
     *
     * @param tasks TaskList containing the Task to be unmarked.
     * @param ui Ui object which interacts with the user.
     * @param storage Storage object which loads and saves tasks.
     * @throws DukeException If storage object is unable to save the list of tasks after unmarking,
     *              or if the taskIndex is not within range of the size of the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(taskIndex);
        String message = ui.taskUnmarkedMessage(task, tasks);
        storage.save(tasks);
        return message;
    }

    /**
     * Returns false, because delete is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
