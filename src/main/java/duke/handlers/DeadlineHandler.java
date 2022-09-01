package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Deadline;
import duke.models.Task;
import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class DeadlineHandler {
    /**
     * Handles the deadline Duke command.
     * Adds a Deadline into the provided list containing description and by date provided in input.
     * @param list: TaskList to add the Deadline in.
     **/
    public static void handle(TaskList list, String input) throws DukeException {
        String[] deadlineInputs = input.split(" /by ", 2);
        if (deadlineInputs.length < 2 || deadlineInputs[1].trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        Task newTask = new Deadline(deadlineInputs[0], deadlineInputs[1]);
        list.add(newTask);
        dukePrint("Deadline added!");
    }
}
