package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the command that is executed when the user inputs <code>unmark</code>.
 *
 * @author njxue
 * @version v0.1
 */
public class UnmarkCommand extends Command {

    /** The index of the task in the TaskList to be unmarked. */
    private int taskIndex;

    /**
     * Returns the format of the <code>unmark</code> command.
     *
     * @return The format of the <code>unmark</code> command.
     */
    public static String getFormat() {
        return "unmark <Integer>";
    }
    
    /**
     * Creates an <code>UnmarkCommand</code>.
     *
     * @param taskIndex The index of the task in the TaskList to be unmarked as done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the <code>unmark</code> command. Deletes the specific task in the TaskList.
     *
     * @param tasks <code>TaskList</code> containing the <code>Task</code> to be unmarked.
     * @param ui <code>Ui</code> object which interacts with the user.
     * @param storage <code>Storage</code> object which loads and saves tasks.
     * @throws DukeException If storage object is unable to save the list of tasks after unmarking, 
     * or if the <code>taskIndex</code> is not within range of the size of the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(this.taskIndex);
        ui.showUnmarkTask(task, tasks);
        storage.save(tasks);
    }

    /**
     * Returns false, because <code>delete</code> is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
