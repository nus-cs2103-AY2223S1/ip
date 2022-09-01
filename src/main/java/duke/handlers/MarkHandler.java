package duke.handlers;

import duke.models.Task;
import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class MarkHandler {
    /**
     * Handles the mark Duke command.
     * Marks a Task as done from the provided list based on position index provided in input.
     * @param list: TaskList containing the Tasks to mark.
     **/
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
