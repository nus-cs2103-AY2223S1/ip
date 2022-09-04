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
     * {@inheritDoc}
     * This command exits Duke.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        super.setExit();
        try {
            tasks.writeToFile(storage);
            return Ui.goodbye();
        } catch (IOException e) {
            throw new DukeException(Ui.saveFail());
        }
    }
}
