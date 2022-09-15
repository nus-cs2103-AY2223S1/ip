package violet.command;

import violet.TaskList;
import violet.Ui;
import violet.exception.VioletException;
import violet.task.Task;

/**
 * PriorityCommand class executes the priority command.
 */
public class PriorityCommand extends Command {
    /** The input given by the user. */
    private String input;

    /**
     * Instantiates the PriorityCommand object.
     *
     * @param input The input given by the user.
     */
    public PriorityCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws VioletException {
        String[] inputs = input.split(" ");

        if (inputs.length < 3) {
            throw new VioletException("Please enter the priority command as follows: priority <index> "
                    + "<Priority Level>\n" + "E.g priority 2 high");
        }

        int index = Integer.parseInt(inputs[1]);
        assert index > 0 : "index must be > 0";

        if (index <= 0 || index > taskList.size()) {
            throw new VioletException("Sorry... There's no such index in the list. Please try again.");
        }

        Task task = taskList.get(index - 1);
        task.setPriorityStatus(inputs[2].toUpperCase());

        this.response = "Alright, I've set the priority for this task to "
                + inputs[2].toUpperCase() + ":\n" + task.toString() + "\n";
    }
}
