package duke.commands;

import java.io.IOException;

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
     * @param ui Ui to interact with the user.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.setExit();
        try {
            tasks.writeToFile(storage);
            ui.goodbye();
        } catch (IOException e) {
            ui.saveFail();
        }
    }
}
