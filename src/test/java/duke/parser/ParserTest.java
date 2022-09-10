package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class ParserTest {

    @Test
    public void parseDateTest() {
        assertEquals(Parser.parseLocalDate("Aug 06 2015"), "2015-08-06");
    }

}
