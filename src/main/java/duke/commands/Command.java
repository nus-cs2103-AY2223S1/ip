package duke.commands;

import duke.exceptions.DukeException;

/**
 * Abstract class to represent Commands.
 */
public abstract class Command {

    public abstract void executeCommand() throws DukeException;
}
