package duke.commands;

import duke.commons.Storage;
import duke.commons.TaskList;
import duke.commons.Ui;
import duke.exceptions.DukeException;

/**
 * This class performs termination command.
 */
public class ByeCommand implements Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        javafx.application.Platform.exit();
        storage.saveToFile(tasks);
        return Ui.formatFarewellMessage();
    }
}
