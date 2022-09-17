package duke.command;

import duke.DukeException;
import duke.Response;
import duke.util.TaskList;

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
        try {
            int index = Integer.parseInt(input) - 1;
            taskList.getTask(index).setDone();
            builder.append("I've marked this task as done! \n  "
                    + taskList.getTask(index).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds!");
        } catch (NumberFormatException e) {
            throw new DukeException("A number should be provided after the command 'mark'");
        }
    }
}
