package zeus.command;

import zeus.Ui;
import zeus.ZeusException;
import zeus.storage.Storage;
import zeus.task.Task;
import zeus.task.TaskList;

/**
 * Represents a command to mark a <code>Task</code> as done.
 *
 * @author Derrick Khoo
 */
public class MarkDoneCommand extends Command {
    private int index;

    /**
     * Constructs a command to mark a <code>Task</code> as done.
     *
     * @param index the index of the task, based on 1-based indexing from user perspective.
     */
    public MarkDoneCommand(int index) {
        this.index = index;
    }
    /**
     * Handles the command to mark a <code>Task</code> as done.
     *
     * @param storage  the <code>Storage</code> dealing with loading and saving tasks in a file
     * @param ui       the user interfaces that deals with user inputs
     * @param taskList the list of tasks
     * @throws ZeusException if there is an error marking the task as done
     */
    public String handle(Storage storage, Ui ui, TaskList taskList) throws ZeusException {
        Task t = taskList.getTask(index);
        t.markDone();
        return "Zeus says:\n" + ui.formatMessage("Nice, I've marked this task as done:\n"
                + t);
    }
}
