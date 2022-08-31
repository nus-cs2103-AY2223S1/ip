package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Deadline;
import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class DeadlineHandler {

    public static void handle(TaskList taskList, String input) throws DukeException {
        String[] deadline = input.split("/", 2);
        if (deadline.length < 2 || deadline[1].trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String taskDescription = deadline[0];
        String taskDeadline = deadline[1].split(" ", 2)[1];
        taskList.add(new Deadline(taskDescription, taskDeadline));
        dukePrint("Got it. I've added this task:\n" + taskList.get(taskList.size()-1).toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
