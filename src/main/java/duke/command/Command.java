package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a Command object that is specified by the user.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
