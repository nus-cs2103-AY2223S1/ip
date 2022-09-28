package violet.command;

import violet.exception.VioletException;
import violet.TaskList;
import violet.Ui;
import violet.task.Deadline;

/**
 * DeadlineCommand class to execute the deadline command.
 */
public class DeadlineCommand extends Command {
    /** The input provided by the user. */
    private String input;

    /**
     * Instantiates the DeadlineCommand object.
     *
     * @param input Input provided by the user.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws VioletException {
        String[] deadlineArray = input.split("deadline", 2);
        String taskBy = deadlineArray[1];
        String[] taskDeadline = taskBy.split("/by", 2);

        if (deadlineArray[1].isEmpty()) {
            throw new VioletException("Please give me the description of the deadline.\n");
        }

        if (taskDeadline.length < 2 || taskDeadline[1].isEmpty()) {
            throw new VioletException("Please include a /by for your deadline. "
                    + "E.g /by 2019-10-15T10:15:00.\n");
        } else {
            Deadline d = new Deadline(taskDeadline[0].substring(1), taskDeadline[1]);
            int index = taskList.size() + 1;
            taskList.add(d);
            this.response = "Got it. I've added this task:\n" + d.toString()
                    + "\n" + "Now you have " + index + " tasks in this list.";
        }
    }
}
