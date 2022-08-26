package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class performs list all tasks stored in TaskList command.
 */
public class ListCommand implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.printListStatus(taskList);
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
