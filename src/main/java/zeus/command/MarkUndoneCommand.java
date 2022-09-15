package zeus.command;

import zeus.Ui;
import zeus.ZeusException;
import zeus.storage.Storage;
import zeus.task.Task;
import zeus.task.TaskList;

/**
 * Represents a command to mark a <code>Task</code> as undone.
 *
 * @author Derrick Khoo
 */
public class MarkUndoneCommand extends Command {
    private int index;

    /**
     * Constructs a command to mark a <code>Task</code> as undone.
     *
     * @param index the index of the task, using 1-based indexing from user perspective.
     */
    public MarkUndoneCommand(int index) {
        this.index = index;
    }

    /**
     * Handles the command to mark a <code>Task</code> as undone.
     * @param storage  the <code>Storage</code> dealing with loading and saving tasks in a file
     * @param ui       the user interfaces that deals with user inputs
     * @param taskList the list of tasks
     * @throws ZeusException if there is an error marking the task as undone.
     */
    public String handle(Storage storage, Ui ui, TaskList taskList) throws ZeusException {
        Task t = taskList.getTask(index);
        t.markUndone();
        return "Zeus says:\n" + ui.formatMessage("OK, I've marked this task as not done yet:\n"
                + t);
    }
}
