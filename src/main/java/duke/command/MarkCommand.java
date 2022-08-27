package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the command that is executed when the user inputs mark.
 *
 * @author njxue
 * @version v0.1
 */
public class MarkCommand extends Command {
    /** The index of the task in the TaskList to be marked. */
    private int taskIndex;

    /**
     * Creates a MarkCommand.
     *
     * @param taskIndex The index of the task in the TaskList to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Returns the format of the mark command.
     *
     * @return The format of the mark command.
     */
    public static String getFormat() {
        return "mark <Integer>";
    }

    /**
     * Executes the mark command. Deletes the specific task in the TaskList.
     *
     * @param tasks TaskList containing the task to be marked.
     * @param ui Ui object which interacts with the user.
     * @param storage Storage object which loads and saves tasks.
     * @throws DukeException If storage object is unable to save the list of tasks after marking,
     *              or if the taskIndex is not within range of the size of the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.mark(taskIndex);
        ui.showMarkTask(task, tasks);
        storage.save(tasks);
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
