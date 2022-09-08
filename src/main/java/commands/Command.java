package commands;

import duke.DukeException;
import duke.Parser;

/**
 * Interface for all Commands
 */
interface Command {
    void execute(Parser parser) throws DukeException;
}
