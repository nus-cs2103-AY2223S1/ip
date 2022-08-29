package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void validCommandTest() {
        assertEquals(CommandType.DEADLINE, Parser.parse("deadline dummy deadline /by 2022-01-03"));
    }

    @Test
    public void invalidCommandTest() {
        assertEquals(CommandType.UNABLE, Parser.parse("this is a invalid command"));
    }
}
