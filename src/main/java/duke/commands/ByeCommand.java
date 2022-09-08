package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit Duke.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Saves the task list to the hard drive.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save tasks to a local file.
     * @return A string containing a goodbye message.
     * @throws DukeException If the task list could not be saved to the hard drive.
     */
    private static String writeToFile(TaskList tasks, Storage storage) throws DukeException {
        try {
            tasks.writeToFile(storage);
            return Ui.goodbye();
        } catch (IOException e) {
            throw new DukeException(Ui.saveFail());
        }
    }

    /**
     * {@inheritDoc}
     * This command exits Duke and saves the task list to the hard drive.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        super.setExit();
        return writeToFile(tasks, storage);
    }
}
