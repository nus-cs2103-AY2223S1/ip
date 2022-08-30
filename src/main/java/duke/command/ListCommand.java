package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to list out all the tasks in the list of tasks.
 *
 * @author Derrick Khoo
 */
public class ListCommand extends Command {

    /**
     * Handles the command to list out all <code>Task</code> in the list of tasks.
     * @param storage  the <code>Storage</code> dealing with loading and saving tasks in a file
     * @param ui       the user interfaces that deals with user inputs
     * @param taskList the list of tasks
     * @throws DukeException if there is currently no tasks in the list of tasks.
     */
    @Override
    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        int numOfTasks = taskList.getSize();
        if (numOfTasks == 0) {
            throw new DukeException("Unfortunately, you do not have any tasks at hand."
                    + " Try creating some first.");
        }
        ui.line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= numOfTasks; i++) {
            Task t = taskList.getTask(i);
            System.out.println(i + "." + t);
        }
        ui.line();
    }
}
