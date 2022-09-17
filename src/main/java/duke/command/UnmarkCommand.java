package duke.command;

import duke.DukeException;
import duke.Response;
import duke.util.TaskList;

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
        try {
            int index = Integer.parseInt(input) - 1;
            taskList.getTask(index).setUndone();
            builder.append("I've marked this task as not done.. \n  "
                    + taskList.getTask(index).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds!");
        } catch (NumberFormatException e) {
            throw new DukeException("A number should be provided after the command 'unmark'");
        }
    }
}
