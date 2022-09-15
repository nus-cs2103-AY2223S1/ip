package action;

import duke.DukeException;

import task.TaskList;

/**
 * Delete is a class that removes a task from the TaskList.
 */
public class Delete {

    /**
     * Deletes the specified index of task from TaskList.
     * @param str Array of String of user input to check which index to delete.
     * @param taskList The TaskList to delete the task from.
     * @return The reply of the system when a task is deleted.
     */

    public static String delete(String[] str, TaskList taskList) {
        assert taskList.getTaskList().size() > 0 : "TaskList should not be empty";
        try {
            int pos = Integer.parseInt(str[1]) - 1;
            return taskList.delete(pos);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("");
        }
    }

}
