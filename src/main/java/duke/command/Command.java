package duke.command;

import duke.DukeException;

public abstract class Command {
    public abstract void execute() throws DukeException;
}
