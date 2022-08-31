package duke.handlers;

import duke.models.Task;
import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class DeleteHandler {
    public static void handle(TaskList taskList, String input) {
        int taskNo = Integer.parseInt(input);
        try {
            Task task = taskList.get(taskNo - 1);
            taskList.remove(taskNo - 1);
            dukePrint("Noted. I've removed this task:\n" + task.toString() +
                    "\nNow you have " + taskList.size() + " tasks left in the list.");
        } catch (IndexOutOfBoundsException e) {
            int taskListSize = taskList.size();
            dukePrint(String.format("List size is (%s). Please enter a valid input", taskListSize));
        }
    }
}
