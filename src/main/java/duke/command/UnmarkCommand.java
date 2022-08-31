package duke.command;

import duke.DukeException;
import duke.Response;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command 'unmark'.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * Constructor for a new unmark command.
     *
     * @param input the input
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Runs the command 'unmark'.
     * @inheritDoc
     * @param taskList List of current tasks.
     * @param builder
     * @throws DukeException Duke Exception for unmarking out of bounds.
     */
    @Override
    public void run(TaskList taskList, Response builder) throws DukeException {
        int index = Integer.parseInt(input) - 1;
        try {
            taskList.getTask(index).setUndone();
            builder.append("I've marked this task as not done.. \n  "
                    + taskList.getTask(index).toString());
        } catch (IndexOutOfBoundsException e) {
            builder.append("Index is out of bounds!");
            throw new DukeException("Index is out of bounds!");
        }
    }
}
