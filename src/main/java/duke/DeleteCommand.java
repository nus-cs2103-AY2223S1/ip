package duke;

import java.util.ArrayList;
/**
 * Represents a command which deletes the task
 */
public class DeleteCommand extends Command {
    /**
     * Handles exception when the task number inputted by user is too large or is a non-positive number.
     * @param index Index that the user has keyed in
     * @param listOfTask List that stores the tasks
     * @throws DukeIndexTooLargeException
     * @throws DukeNonPositiveIndexException
     */
    void handleException(int index, ArrayList<Task> listOfTask) throws DukeIndexTooLargeException, DukeNonPositiveIndexException {
        if (index > listOfTask.size() - 1) {
            throw new DukeIndexTooLargeException();
        }
        if (index <= -1) {
            throw new DukeNonPositiveIndexException();
        }
    }
    /**
     * Delete a task.
     * @param fullCommand Full command that user inputted to delete a task.
     * @param listOfTasks List where tasks are stored.
     * @param ui Ui object that is used to interact with users.
     * @param storage Storage to store the file of the list of tasks.
     * @return A string that would be outputted to the screen when user marks a task.
     */
    @Override
   String execute(String fullCommand, ArrayList<Task> listOfTasks, Ui ui, Storage storage) {
        try {
            TaskList taskList = new TaskList(listOfTasks);
            int index = Integer.parseInt(fullCommand.substring(7)) - 1;
            handleException(index, listOfTasks);
            return taskList.delete(fullCommand);
        } catch (DukeException e) {
           return e.getMessage();
        }
   }
}
