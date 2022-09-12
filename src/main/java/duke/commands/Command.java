package duke.commands;

import duke.DukeException;

public abstract class Command {

    public abstract String execute() throws DukeException;
}
