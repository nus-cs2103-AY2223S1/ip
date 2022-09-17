package duke.command;

import duke.DukeException;
import duke.Response;
import duke.util.TaskList;

/**
 * Handles the command 'delete'.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Constructor for a new Delete command.
     *
     * @param input the input
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Runs the command 'delete'.
     * @inheritDoc
     * @param taskList List of current tasks.
     * @param builder
     * @throws DukeException Duke Exception for deleting out of bounds.
     */
    @Override
    public void run(TaskList taskList, Response builder) throws DukeException {

        try {
            int index = Integer.parseInt(input) - 1;
            taskList.deleteTask(index, builder);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds");
        } catch (NumberFormatException e) {
            throw new DukeException("A number should be provided after the command 'delete'");
        }
    }
}
