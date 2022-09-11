package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

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
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        String[] inputs = input.split(" ");
        int index = Integer.parseInt(inputs[1]);
        assert index > 0 : "index must be > 0";
        if (index <= 0 || index > taskList.size()) {
            throw new DukeException("Sorry I can't set a priority to a task that does not exist :(");
        }

        Task task = taskList.get(index - 1);
        task.setPriorityStatus(inputs[2].toUpperCase());
        this.response = "OK, I've set the priority for this task to "
                + inputs[2].toUpperCase() + ":\n" + task.toString() + "\n";
    }
}
