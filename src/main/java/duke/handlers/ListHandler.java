package duke.handlers;

import duke.models.Task;
import duke.models.TaskList;

public class ListHandler {
    /**
     * Handles the LIST command.
     * Prints out all the tasks in the TaskList.
     * @param taskList TaskList containing the list of tasks.
     */
    public static String getResponse(TaskList taskList) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            response.append(i).append(". ").append(task.toString()).append("\n");
        }
        return response.toString();
    }
}
