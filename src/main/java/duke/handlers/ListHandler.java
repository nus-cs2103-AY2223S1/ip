package duke.handlers;

import duke.models.TaskList;
import duke.models.Task;
import duke.services.Ui;

import static duke.services.Ui.dukePrint;

public class ListHandler {
    public static void handle(TaskList taskList) {
        dukePrint("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            Ui.dukePrint(i + ". " + task.toString());
        }
    }
}
