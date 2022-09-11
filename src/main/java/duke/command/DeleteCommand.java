package duke.command;

import duke.exception.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * DeleteCommand class to execute the delete command.
 */
public class DeleteCommand extends Command {
    /** The input given by the user. */
    private String input;

    /**
     * Instantiates the DeleteCommand object.
     *
     * @param input The input given by the user.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        String deletionIndex = input.substring(7);
        int index = Integer.parseInt(deletionIndex);
        assert index > 0 : "index must be > 0";
        if (index <= 0 || index > taskList.size()) {
            throw new DukeException("OOPS!!! I can't remove this if it does not exist\n");
        }
        Task task = taskList.remove(index - 1);
        this.response = "Noted. I've removed this task:" + task.toString() + "\n" + "Now you have "
                + taskList.size() + " tasks in the list.";
    }
}
