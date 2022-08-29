package duke.parser;

import duke.command.ByeCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    public void parseTest() throws DukeException {
        assertEquals(new ByeCommand(), Parser.parse("bye"));
    }
}
