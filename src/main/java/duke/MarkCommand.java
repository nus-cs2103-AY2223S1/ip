package duke;

import java.util.ArrayList;

/**
 * Represents a command which marks the task
 */
public class MarkCommand extends Command {
    /**
     * Handles exceptions where user keys in an index which is too large or non-positive.
     * @param index Index that the user has keyed in
     * @param listOfTask List that stores the tasks
     * @throws DukeIndexTooLargeException
     * @throws DukeNonPositiveIndexException
     */
    void handleException(int index, ArrayList<Task> listOfTask) throws
            DukeIndexTooLargeException, DukeNonPositiveIndexException {
        if (index > listOfTask.size() - 1) {
            throw new DukeIndexTooLargeException();
        }
        if (index <= -1) {
            throw new DukeNonPositiveIndexException();
        }
    }

    /**
     * Marks a task
     * @param taskName Name of task.
     * @param listOfTask List that stores the tasks.
     * @param ui Ui object that is used to interact with users.
     * @param storage Storage to store the file of the list of tasks.
     * @return A string that would be outputted to the screen when user marks a task.
     */
    @Override
    String execute(String taskName, ArrayList<Task> listOfTask, Ui ui, Storage storage) {
        try {
            int taskNumber = getTaskNumberOfTaskToBeMarked(taskName); 
            handleException(taskNumber, listOfTask); 
            Task task = getTaskToBeMarked(taskNumber, listOfTask);
            assert task != null;
            return task.mark();
        } catch (DukeException e) {
           return e.getMessage();
        }
    }

    /**
     Gets the task number to be marked.
     * @param taskName Name of the task to be marked.
     * @return Task number of task which is to be marked.
     */
    int getTaskNumberOfTaskToBeMarked(String taskName) {
       return Integer.parseInt(taskName.substring(5)) - 1;
    }

    /**
     * Gets the task to be marked
     * @param taskNumber Task number of task which is to be marked.
     * @param listOfTask List which stores the tasks.
     * @return Task to be marked.
     */
    Task getTaskToBeMarked(int taskNumber, ArrayList<Task> listOfTask) {
        return listOfTask.get(taskNumber);
    }
}