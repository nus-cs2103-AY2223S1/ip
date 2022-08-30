package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to delete a <code>Task</code> in the list of tasks.
 *
 * @author Derrick Khoo
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a command to delete a <code>Task</code> from the list of tasks.
     *
     * @param index  the index of the task deleted, in accordance to 1-based indexing
     *               from user perspective.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Handles the command to delete a <code>Task</code>.
     *
     * @param storage  the <code>Storage</code> dealing with loading and saving tasks in a file
     * @param ui       the user interfaces that deals with user inputs
     * @param taskList the list of tasks
     * @throws DukeException if there is an error deleting the <code>Task</code> from the list of tasks.
     */
    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        Task t = taskList.deleteTask(this.index);
        ui.line();
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        taskList.printArraySize();
        ui.line();
    }
}
