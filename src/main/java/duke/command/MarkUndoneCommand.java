package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

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
     * @throws DukeException if there is an error marking the task as undone.
     */
    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        Task t = taskList.getTask(index);
        t.markUndone();
        ui.line();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        ui.line();
    }
}
