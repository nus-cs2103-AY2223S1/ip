package duke.command;

import duke.Response;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * DeadlineCommand represents a command to add a DeadLine to the TaskList.
 */
public class DeadlineCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    /**
     * Creates a DeadlineCommand to add a DeadLine to the TaskList.
     *
     * @param taskList The TaskList to add the DeadLine to.
     * @param inputArr The input String array.
     */
    public DeadlineCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    /**
     * Adds the Deadline to the TaskList.
     *
     * @return The Response to be displayed.
     * @throws DukeException If the input array is invalid.
     */
    @Override
    public Response execute() throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] descriptionDate = inputArr[1].split(" /by ", 2);
        if (descriptionDate.length < 2) {
            throw new DukeException("The description and date of a deadline cannot be empty.");
        }
        String task = descriptionDate[0];
        String date = descriptionDate[1];
        Deadline event = new Deadline(task, date);
        taskList.addTask(event);
        return new Response("Got it. I've added this task: \n"
                + event + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.\n");
    }
}
