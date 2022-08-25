package duke.parser;

import duke.data.exception.DukeException;
import duke.parser.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommandTest1() throws DukeException {
        String testString = "bye";

        assertEquals(true, Parser.parseCommand(testString).isEnd());

    }

}
