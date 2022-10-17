package duke.commands;

import duke.commons.Storage;
import duke.commons.TaskList;
import duke.commons.Ui;
import duke.exceptions.DukeException;

/**
 * This class performs list all tasks stored in TaskList command.
 */
public class ListCommand implements Command {
    public static final String COMMAND_WORD = "list";

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        return Ui.formatListStatusMessage(taskList);
    }
}
