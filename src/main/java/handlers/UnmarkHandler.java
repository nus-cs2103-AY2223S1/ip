package handlers;

import models.Task;
import models.TaskList;

import static services.Ui.dukePrint;

public class UnmarkHandler {
    public static void handle(TaskList list, String input) {
        int taskNum = Integer.parseInt(input) - 1;
        try {
            Task t = list.get(taskNum);
            t.markAsDone();
            dukePrint("Nice! I've marked this tasks as done:\n" + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            int listSize = list.size();
            dukePrint(String.format("Please enter a valid number from 1 to %d.%n", listSize));
        }
    }
}
