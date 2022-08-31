package duke.command;

import duke.exception.DukeException;
import duke.response.Response;
import duke.task.Deadline;
import duke.task.TasksList;

/**
 * Represents a command to add a Deadline to the tasksList.
 */
public class DeadlineCommand extends Command {
    private static final String DELIMITER = " /by ";
    private static final String DEADLINE_MSG = "Got it. I've added this task:\n";
    private String[] inputArray;
    private TasksList tasksList;


    /**
     * Creates a new DeadlineCommand instance.
     * @param tasksList The TasksList to add the Deadline to.
     * @param inputArray The array that represents the user input.
     */
    public DeadlineCommand(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    /**
     * Adds the Deadline to the TasksList.
     * @return The Response to be displayed upon the execution of the command.
     * @throws DukeException If the Deadline cannot be added to the TasksList.
     */
    @Override
    public Response execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty!");
        }

        // split again to get date/time
        String[] splitArray = this.inputArray[1].split(DeadlineCommand.DELIMITER, 2);

        if (splitArray.length < 2) {
            throw new DukeException("Please enter a due date for this task!");
        }

        // Make a new deadline object
        Deadline deadline = new Deadline(splitArray[0], splitArray[1]);
        this.tasksList.addToList(deadline);
        StringBuilder sb = new StringBuilder();
        sb.append(DeadlineCommand.DEADLINE_MSG + deadline + "\n" + "Now you have ");
        if (this.tasksList.getLength() <= 1) {
            sb.append(this.tasksList.getLength() + " task in the list.\n");
        } else {
            sb.append(this.tasksList.getLength() + " tasks in the list.\n");
        }
        return new Response(sb.toString());
    }
}


