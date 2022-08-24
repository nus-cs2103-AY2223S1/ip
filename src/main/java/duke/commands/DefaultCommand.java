package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents all other commands not understood by Duke.
 */
public class DefaultCommand extends Command {

    /**
     * {@inheritDoc}
     * This command sends a message to the user whenever they
     * enter a command that Duke cannot understand,
     * or if the message was empty.
     *
     * @param tasks Contains the task list.
     * @param ui Ui to interact with the user.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.invalidInput();
    }

}
