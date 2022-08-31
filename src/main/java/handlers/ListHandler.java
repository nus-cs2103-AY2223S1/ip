package handlers;

import models.TaskList;
import models.Task;
import services.Ui;

import static services.Ui.dukePrint;

public class ListHandler {
    public static void handle(TaskList taskList) {
        dukePrint("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            Ui.dukePrint(i + ". " + task.toString());
        }
    }
}
