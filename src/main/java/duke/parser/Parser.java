package duke.parser;

import duke.DukeException;
import duke.commands.Command;

public interface Parser {
    Command parse(String arguments) throws DukeException;
}
