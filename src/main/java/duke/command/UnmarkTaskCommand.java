package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * Handles an "unmark" command.
 * @author Jason
 */
public class UnmarkTaskCommand extends Command {
    private String taskIndex;

    public UnmarkTaskCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Unmarks a task in the list.
     * @param taskList TaskList to update the task that is unmarked.
     * @param storage Storage to save unmarked task.
     * @exception DukeException Index out of bounds or task at index has not been created.
     */
    @Override
    public void run(TaskList taskList, Storage storage) throws DukeException, IOException {
        int index = Integer.parseInt(taskIndex) - 1;

        try {
            taskList.get(index).markAsUndone();
            String message = "OK, I've marked this task as not done yet: \n  "
                    + taskList.get(index) + "\n";
            Ui.printMessage(message);
        } catch (NullPointerException e) {
            throw new DukeException("☹ OOPS!!! There is no task created for this index!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please enter a valid index number!");
        }

        //Saving data
        storage.saveData(taskList.getList());
    }
}
