package jUnitTests;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParseMarkTaskCommandTest {

    @Test
    void shouldParseMarkTask() throws DukeException {
        Command c = Parser.parse("mark 1");
        assertFalse(c.isExit());
    }
}
