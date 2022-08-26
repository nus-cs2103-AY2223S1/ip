package duke.command;

import duke.DukeException;
import duke.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks) throws DukeException;
}
