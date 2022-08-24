package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class performs the required actions for Duke to list out all the tasks stored.
 */
public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.listTasks(taskList);
        } catch (DukeException e) {
            ui.handleException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ListCommand) {
            return true;
        }
        return false;
    }
}
