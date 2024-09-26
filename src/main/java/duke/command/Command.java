package duke.command;

import duke.TaskList;
import duke.DukeException;

public abstract class Command {
    public Command() {
    }

    public abstract String executeWithMessage(TaskList tasks) throws DukeException;
}
