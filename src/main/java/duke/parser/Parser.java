package duke.parser;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a Parser class
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class Parser {

    /**
     * Parses the string.
     * @param s string to be parsed
     * @return Command
     * @throws DukeException if it occurs
     */
    public static Command parse(String s) throws DukeException {
        return Command.of(s);
    }
}
