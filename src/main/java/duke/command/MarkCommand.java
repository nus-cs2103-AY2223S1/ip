package duke.command;

import duke.DukeException;
import duke.Response;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command 'mark'.
 */
public class MarkCommand extends Command {
    private String input;

    /**
     * Constructor for a new Mark command.
     *
     * @param input the input
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Runs the command 'mark'.
     * @inheritDoc
     * @param taskList List of current tasks.
     * @param builder
     * @throws DukeException Duke Exception for marking out of bounds.
     */
    @Override
    public void run(TaskList taskList, Response builder) throws DukeException {
        int index = Integer.parseInt(input) - 1;
        try {
            taskList.getTask(index).setDone();
            builder.append("I've marked this task as done! \n  "
                    + taskList.getTask(index).toString());
        } catch (IndexOutOfBoundsException e) {
            builder.append("Index is out of bounds!");
            throw new DukeException("Index is out of bounds!");
        }
    }
}
