package duke.handlers;

import duke.models.Task;
import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class DeleteHandler {
    /**
     * Handles the DELETE Duke command.
     * Deletes a Task from the provided list based on position index provided in input.
     *
     * @return Response of the executed DELETE command.
     * @param list: TaskList containing the Tasks to delete.
     * @param input: Task number in the given list to delete.
     **/
    public static String getResponse(TaskList list, String input) {
        int taskNum = Integer.parseInt(input) - 1;
        try {
            Task t = list.remove(taskNum);
            String tasksLeft = String.format("Now you have %d task(s) in the list.", list.size());
            String response = String.join("\n",
                    "Got it. I've deleted this task:",
                    t.toString(),
                    tasksLeft);
            return (response);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            int listSize = list.size();
            return (String.format("Please enter a valid number from 1 to %d.%n", listSize));
        }
    }
}
