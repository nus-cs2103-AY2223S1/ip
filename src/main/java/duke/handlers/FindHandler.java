package duke.handlers;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.models.Task;
import duke.models.TaskList;

/**
 * A handler class for Find commands.
 */
public class FindHandler {
    /**
     * Handles the FIND Duke command.
     * Finds Tasks containing the specified String in given input.
     *
     * @param list TaskList containing the Tasks to unmark.
     * @param input Task number to unmark as done, in String format.
     * @return Response of the executed FIND Command.
     **/
    public static String getResponse(TaskList list, String input) throws DukeException {
        boolean isTaskFound = false;
        ArrayList<Task> foundTasksList = new ArrayList<>();
        if (list.size() == 0) {
            throw new DukeException("☹ OOPS!!! Tasklist is empty!");
        }
        for (Task task: list) {
            if (task.toString().contains(input)) {
                foundTasksList.add(task);
                isTaskFound = true;
            }
        }
        if (isTaskFound) {
            assert foundTasksList.size() > 0;
            StringBuilder response = new StringBuilder(("Here are the tasks found"));
            for (int i = 0; i < foundTasksList.size(); i++) {
                response.append(i).append(1).append(". ").append(foundTasksList.get(i)).append("\n");
            }
            return response.toString();
        } else {
            return ("Tasks not found with given keyword");
        }
    }
}
