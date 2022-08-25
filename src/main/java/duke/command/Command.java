package duke.command;

import duke.exception.DukeException;
import duke.processor.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks) throws DukeException;
}
