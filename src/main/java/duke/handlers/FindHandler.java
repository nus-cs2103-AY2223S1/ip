package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Task;
import duke.models.TaskList;

import java.util.ArrayList;

import static duke.services.Ui.dukePrint;

public class FindHandler {
    public static void handle(TaskList taskList, String input) throws DukeException {
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
            dukePrint("Here are the matching tasks in your list:");
            for (int i = 1; i <= resultList.size(); i++) {
                Task foundTask = resultList.get(i - 1);
                dukePrint(i + ". " + foundTask.toString());
            }
        } else {
            dukePrint("No tasks found with keyword");
        }
    }
}
