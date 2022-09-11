package duke.command;

import duke.exception.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

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
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        String taskNumber = input.substring(5);
        int number = Integer.parseInt(taskNumber);
        assert number > 0 : "index must be > 0";
        if (number <= 0 || number > taskList.size()) {
            throw new DukeException("OOPS!!! Sorry,"
                    + " I can't mark this as done if it does not exist :(\n");
        }

        Task task = taskList.get(number - 1);
        task.markAsDone();
        this.response = "Nice! I've marked this task as done:\n" + task.toString() + "\n";
    }
}
