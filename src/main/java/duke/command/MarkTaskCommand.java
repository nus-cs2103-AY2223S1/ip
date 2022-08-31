package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * Handles the "mark" command.
 * @author Jason
 */
public class MarkTaskCommand extends Command {
    private String taskIndex;

    public MarkTaskCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks a task in the list.
     * @param taskList TaskList to update the task that is marked.
     * @param storage Storage to save marked task.
     * @throws DukeException Index out of bounds or task at index has not been created
     */
    @Override
    public void run(TaskList taskList, Storage storage) throws DukeException, IOException {
        int index = Integer.parseInt(taskIndex) - 1;
        try {
            taskList.get(index).markAsDone();
            String message = "Nice! I've marked this task as done: \n  "
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
