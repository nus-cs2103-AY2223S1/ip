package duke;

import java.util.ArrayList;

/**
 * Represents a command which unmarks the task
 */
public class UnmarkCommand extends Command {
    /**
     * Handles exceptions where user keys in an index which is too large or non positive.
     * @param index Index that the user has keyed in
     * @param listOfTask List that stores the tasks
     * @throws DukeIndexTooLargeException
     * @throws DukeNonPositiveIndexException
     */
    void handleException(int index, ArrayList<Task> listOfTask)
            throws DukeIndexTooLargeException, DukeNonPositiveIndexException {
        if (index > listOfTask.size() - 1) {
            throw new DukeIndexTooLargeException();
        }
        if (index <= -1) {
            throw new DukeNonPositiveIndexException();
        }
    }

    /**
     * Unmarks a task.
     * @param taskName Name of task.
     * @param listOfTask List that stores the tasks.
     * @param ui Ui object that is used to interact with users.
     * @param storage Storage to store the file of the list of tasks.
     * @return A string that would be outputted to the screen when user unmarks a task.
     */
    @Override
    String execute(String taskName, ArrayList<Task> listOfTask, Ui ui, Storage storage) {
        try {
        int taskNumber = getTaskNumberOfTaskToBeUnmarked(taskName);
        handleException(taskNumber, listOfTask); 
        Task task = getTaskToBeUnmarked(taskNumber,listOfTask);
        return task.unmark();
        } catch (DukeException e) {
           return e.getMessage(); 
        } 
    }

    /**
     * Gets the task number to be unmarked.
     * @param taskName Name of the task to be unmarked.
     * @return Task number of task which is to be unmarked.
     */
    int getTaskNumberOfTaskToBeUnmarked(String taskName) {
        return Integer.parseInt(taskName.substring(7)) - 1;
    }

    /**
     * Gets the task to be unmarked
     * @param taskNumber Task number of task which is to be unmarked.
     * @param listOfTask List which stores the tasks.
     * @return Task to be unmarked.
     */
    Task getTaskToBeUnmarked(int taskNumber, ArrayList<Task> listOfTask) {
        return listOfTask.get(taskNumber);
    }
}
