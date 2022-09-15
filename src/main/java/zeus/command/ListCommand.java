package zeus.command;

import zeus.Ui;
import zeus.ZeusException;
import zeus.storage.Storage;
import zeus.task.Task;
import zeus.task.TaskList;

/**
 * Represents a command to list out all the tasks in the list of tasks.
 *
 * @author Derrick Khoo
 */
public class ListCommand extends Command {

    /**
     * Handles the command to list out all <code>Task</code> in the list of tasks.
     *
     * @param storage  the <code>Storage</code> dealing with loading and saving tasks in a file
     * @param ui       the user interfaces that deals with user inputs
     * @param taskList the list of tasks
     * @throws ZeusException if there is currently no tasks in the list of tasks.
     */
    @Override
    public String handle(Storage storage, Ui ui, TaskList taskList) throws ZeusException {
        int numOfTasks = taskList.getSize();
        if (numOfTasks == 0) {
            throw new ZeusException("Unfortunately, you do not have any tasks at hand."
                    + " Try creating some first.");
        }
        String output = "Zeus says:\n" + "Here are the tasks in your list\n";
        for (int i = 1; i <= numOfTasks; i++) {
            Task t = taskList.getTask(i);
            output += i + "." + t + "\n";
        }
        return output;
    }
}
