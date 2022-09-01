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
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        return Ui.formatListStatusString(taskList);
    }
}
