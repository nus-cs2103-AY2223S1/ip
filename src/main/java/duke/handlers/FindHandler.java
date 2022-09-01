package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Task;
import duke.models.TaskList;
import duke.models.Todo;

import java.util.ArrayList;

import static duke.services.Ui.dukePrint;

public class FindHandler {
    public static void handle(TaskList list, String input) throws DukeException {
        boolean isTaskFound = false;
        ArrayList<Task> foundTasksList = new ArrayList<>();
        if (list.size()== 0) {
            throw new DukeException("☹ OOPS!!! Tasklist is empty!");
        }
        for (Task task: list) {
            if (task.toString().contains(input)) {
                foundTasksList.add(task);
                isTaskFound = true;
            }
        }
        if (isTaskFound) {
            dukePrint("Here are the tasks found");
            for (int i = 0; i < foundTasksList.size(); i++) {
                System.out.println(i + 1 + ". " + foundTasksList.get(i));
            }
        } else {
            dukePrint("Tasks not found with given keyword");
        }
    }
}
