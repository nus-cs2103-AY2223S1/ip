package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class performs termination command.
 */
public class ByeCommand implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        javafx.application.Platform.exit();
        storage.saveToFile(tasks);
        return Ui.formatFarewellString();
    }
}
