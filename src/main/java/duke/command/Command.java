package duke.command;

import duke.DukeException;
import duke.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks) throws DukeException;
}
