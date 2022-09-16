package duke.handlers;

import duke.models.Task;
import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class UnmarkHandler {
    /**
     * Handles the UNMARK Duke command.
     * Unmarks a Task as done from the provided list based on position index provided in input.
     *
     * @return Response of the executed UNMARK Command.
     * @param list: TaskList containing the Tasks to unmark.
     * @param input: Task number to unmark as done, in String format.
     **/
    public static String getResponse(TaskList list, String input) {
        int taskNum = Integer.parseInt(input) - 1;
        try {
            Task t = list.get(taskNum);
            t.markAsDone();
            return ("Nice! I've marked this tasks as done:\n" + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            int listSize = list.size();
            return (String.format("Please enter a valid number from 1 to %d.%n", listSize));
        }
    }
}
