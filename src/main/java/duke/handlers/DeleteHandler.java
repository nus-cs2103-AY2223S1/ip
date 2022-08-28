package duke.handlers;

import duke.models.Task;
import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class DeleteHandler {
    public static void handle(TaskList list, String input) {
        int taskNum = Integer.parseInt(input) - 1;
        try {
            Task t = list.remove(taskNum);
            String tasksLeft = String.format("Now you have %d task(s) in the list.", list.size());
            String printStr = String.join("\n",
                    "Got it. I've deleted this task:",
                    t.toString(),
                    tasksLeft);
            dukePrint(printStr);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            int listSize = list.size();
            dukePrint(String.format("Please enter a valid number from 1 to %d.%n", listSize));
        }
    }
}
