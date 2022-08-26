package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;

/**
 * Interface for all commands, is able to execute a valid command.
 */
public interface Command {

    void execute() throws DukeException, IOException;
}
