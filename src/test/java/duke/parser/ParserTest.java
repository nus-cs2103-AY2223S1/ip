package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.data.exception.DukeException;

public class ParserTest {

    @Test
    public void parseCommandTest1() throws DukeException {
        String testString = "bye";

        assertEquals(true, Parser.parseCommand(testString).isEnd());

    }

}
