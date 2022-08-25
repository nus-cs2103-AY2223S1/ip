package duke.command;

import duke.DukeException;
import duke.util.TaskList;

public abstract class Command {

    public abstract void run(TaskList taskList) throws DukeException;
}
