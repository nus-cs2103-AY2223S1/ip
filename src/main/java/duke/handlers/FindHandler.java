package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Task;
import duke.models.TaskList;

import java.util.ArrayList;

public class FindHandler {
    public static String getResponse (TaskList taskList, String input) throws DukeException {
        boolean isEmptyList = true;
        ArrayList<Task> resultList = new ArrayList<Task>();

        if (taskList.size()== 0) {
            throw new DukeException("☹ OOPS!!! Tasklist is empty!");
        }

        for (Task task: taskList) {
            if (task.toString().contains(input)) {
                resultList.add(task);
                isEmptyList = false;
            }
        }

        if (!isEmptyList) {
            StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 1; i <= resultList.size(); i++) {
                Task foundTask = resultList.get(i - 1);
                response.append(i).append(". ").append(foundTask.toString()).append("\n");
            }
            return response.toString();
        } else {
            return ("No tasks found with keyword");
        }
    }
}
