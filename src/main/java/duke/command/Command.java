package duke.command;

import duke.exception.DukeException;

public abstract class Command {
    public abstract String execute() throws DukeException;
}
