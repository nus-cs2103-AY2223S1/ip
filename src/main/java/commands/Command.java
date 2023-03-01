package commands;

import duke.DukeException;

/**
 * Abstract Command class.
 */
public abstract class Command {
    public abstract String execute() throws DukeException;

}
