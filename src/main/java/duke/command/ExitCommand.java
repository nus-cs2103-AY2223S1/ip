package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.TaskList;

/**
 * Represents a command that exits the Duke program.
 */
public class ExitCommand extends Command {
    private static final String EXIT_SUCCESS_MESSAGE = "bye bye~~ blob blob ☆（･∀･)つ ☆";

    /**
     * Executes the ExitCommand, displays the farewell message and saves the specified task list
     * to the hard disk with the specified Storage.
     *
     * @param tasks The task list to be saved to the hard disk.
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk.
     * @return Type quit Response containing an exit success message.
     * @throws DukeException If the file could not be saved to the hard disk.
     */
    @Override
    public Response<Void> execute(TaskList tasks, Storage storage) throws DukeException {
        storage.writeToFile(tasks.toSaveFormat());
        return new Response<>(ResponseType.QUIT, EXIT_SUCCESS_MESSAGE);
    }
}
