package action;

import duke.DukeException;

import task.TaskList;

import ui.UI;

/**
 * Delete is a class that removes a task from the TaskList.
 */
public class Delete {

    private static UI ui = new UI();

    /**
     * Deletes the specified index of task from TaskList.
     * @param str Array of String of user input to check which index to delete.
     * @param taskList The TaskList to delete the task from.
     * @return The reply of the system when a task is deleted.
     */

    public static String delete(String[] str, TaskList taskList) {
        assert taskList.getTaskList().size() > 0 : "TaskList should not be empty";
        int pos = 0;
        try {
            pos = Integer.parseInt(str[1]) - 1;
            return taskList.delete(pos);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ui.indexOutOfBounds(pos));
        } catch (NumberFormatException e) {
            throw new DukeException(ui.notNumber());
        }
    }

}
