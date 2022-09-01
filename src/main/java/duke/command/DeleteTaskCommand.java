package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Task;
import duke.TaskList;
import duke.utils.Storage;

/**
 * Handles the "delete" command.
 * @author Jason
 */
public class DeleteTaskCommand extends Command {
    private String taskIndex;

    public DeleteTaskCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes a task from the list.
     * @param taskList TaskList to delete task at index from.
     * @param storage Storage to overwrite previous save after deletion.
     * @return String message of running the "delete" command.
     * @throws DukeException Accessing an index out of bounds of the taskList array.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException, IOException {
        try {
            int i = Integer.parseInt(taskIndex.split(" ", 2)[0]) - 1;
            Task task = taskList.get(i);
            taskList.deleteTask(i);
            int numOfTasks = taskList.size();

            String message = "Noted. I've removed this task: \n  "
                    + task + "\n"
                    + "Now you have " + numOfTasks + " tasks in the list.\n";

            //Saving data
            storage.saveData(taskList.getList());
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please enter a valid index number!");
        }
    }
}
