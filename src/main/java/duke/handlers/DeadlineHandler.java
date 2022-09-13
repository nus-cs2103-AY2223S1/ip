package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Deadline;
import duke.models.TaskList;

public class DeadlineHandler {
    /**
     * Handles the DEADLINE command.
     * Adds Deadline task to TaskList.
     * @param taskList TaskList for the deadline task to be added to.
     * @param input Deadline to be added to TaskList.
     */
    public static String getResponse(TaskList taskList, String input) throws DukeException {
        String[] deadline = input.split("/", 2);
        assert deadline.length == 2: "Invalid input";
        if (deadline.length < 2 || deadline[1].trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String taskDescription = deadline[0];
        String taskDeadline = deadline[1].split(" ", 2)[1];
        taskList.add(new Deadline(taskDescription, taskDeadline));
        return ("Got it. I've added this task:\n"
                + taskList.get(taskList.size()-1).toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
