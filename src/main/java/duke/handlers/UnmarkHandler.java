package duke.handlers;

import duke.models.Task;
import duke.models.TaskList;

/**
 * A handler class for Unmark Commands.
 */
public class UnmarkHandler {
    /**
     * Handles the UNMARK Duke command.
     * Unmarks a Task as done from the provided list based on position index provided in input.
     *
     * @param list TaskList containing the Tasks to unmark.
     * @param input Task number to unmark as done, in String format.
     * @return Response of the executed UNMARK Command.
     **/
    public static String getResponse(TaskList list, String input) {
        int taskNum = Integer.parseInt(input) - 1;
        try {
            Task t = list.get(taskNum);
            t.unmarkAsDone();
            return ("I've unmarked this tasks as done:\n" + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            int listSize = list.size();
            return (String.format("Please enter a valid number from 1 to %d.%n", listSize));
        }
    }
}
