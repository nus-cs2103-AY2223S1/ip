package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the command that is executed when the user inputs <code>mark</code>.
 *
 * @author njxue
 * @version v0.1
 */
public class MarkCommand extends Command {

    /** The index of the task in the TaskList to be marked. */
    private int taskIndex;

    /**
     * Returns the format of the <code>mark</code> command.
     *
     * @return The format of the <code>mark</code> command.
     */
    public static String getFormat() {
        return "mark <Integer>";
    }

    /**
     * Creates a <code>MarkCommand</code>.
     *
     * @param taskIndex The index of the task in the TaskList to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the <code>mark</code> command. Deletes the specific task in the TaskList.
     *
     * @param tasks <code>TaskList</code> containing the task to be marked.
     * @param ui <code>Ui</code> object which interacts with the user.
     * @param storage <code>Storage</code> object which loads and saves tasks.
     * @throws DukeException If storage object is unable to save the list of tasks after marking, 
     * or if the <code>taskIndex</code> is not within range of the size of the TaskList.
     */
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.mark(taskIndex);
        ui.showMarkTask(task, tasks);
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
