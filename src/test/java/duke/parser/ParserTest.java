package duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseDateTest() {
        assertEquals(Parser.parseLocalDate("Aug 06 2015"), "2015-08-06");
    }

}
