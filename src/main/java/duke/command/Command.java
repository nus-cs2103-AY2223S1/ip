package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
