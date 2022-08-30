package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

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
     * @throws DukeException if there is an error marking the task as done
     */
    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        Task t = taskList.getTask(index);
        t.markDone();
        ui.line();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        ui.line();
    }
}
