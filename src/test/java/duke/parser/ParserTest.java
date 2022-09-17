package duke.parser;

import static duke.parser.Parser.parseToTaskIndex;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.IllegalInputException;

public class ParserTest {
    @Test
    public void parseToTaskIndexTest() throws IllegalInputException {
        String str = "1";
        int expected = 1;
        int actual = parseToTaskIndex(str);
        assertEquals(expected, actual);
    }


}
