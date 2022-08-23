package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the command that is executed when the user inputs <code>delete</code>.
 *
 * @author njxue
 * @version v0.1
 */
public class DeleteCommand extends Command {
    
    /** The index of the <code>Task</code> object in the <code>TaskList</code> to be deleted. */
    private int taskIndex;

    /**
     * Returns the format of the <code>delete</code> command.
     *
     * @return The format of the <code>delete</code> command.
     */
    public static String getFormat() {
        return "delete <Integer>";
    }

    /**
     * Creates a <code>DeleteCommand</code>.
     *
     * @param taskIndex Index of the <code>Task</code> object in the <code>TaskList</code> to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the <code>delete</code> command. 
     * Deletes the specific <code>Task</code> object in the <code>TaskList</code>.
     *
     * @param tasks <code>TaskList</code> containing the task to be deleted.
     * @param ui <code>Ui</code> object which interacts with the user.
     * @param storage <code>Storage</code> object which loads and saves tasks.
     * @throws DukeException If storage object is unable to save the list of tasks after marking, 
     * or if the <code>taskIndex</code> is not within range of the size of the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.delete(taskIndex);
        ui.showDeleteTask(task, tasks);
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
