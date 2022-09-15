package violet.command;

import violet.exception.VioletException;
import violet.TaskList;
import violet.Ui;
import violet.task.Task;

/**
 * MarkCommand class to execute the mark command.
 */
public class MarkCommand extends Command {
    /** The input given by the user. */
    private String input;

    /**
     * Instantiates the MarkCommand object.
     *
     * @param input THe input given by the user.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws VioletException {
        String taskNumber = input.substring(5);
        int number = Integer.parseInt(taskNumber);
        assert number > 0 : "index must be > 0";
        if (number <= 0 || number > taskList.size()) {
            throw new VioletException("Sorry... There's no such index in the list. Please try again.\n");
        }

        Task task = taskList.get(number - 1);
        task.markAsDone();
        this.response = "As requested, I've marked this task as done:\n" + task.toString() + "\n";
    }
}
