package handlers;
import models.Task;
import models.TaskList;

import static services.Ui.dukePrint;

public class MarkHandler {
    public static void handle(TaskList taskList, String input) {
        int taskNo = Integer.parseInt(input);
        try {
            Task task = taskList.get(taskNo - 1);
            task.setDone();
            dukePrint("Nice! I've marked this task as done:\n" + "[" + task.getStatusIcon() + "] " + task.getTaskName());
        } catch (IndexOutOfBoundsException e) {
            int taskListSize = taskList.size();
            dukePrint(String.format("List size is %s. Please enter a valid input.", taskListSize));
        }
    }
}
