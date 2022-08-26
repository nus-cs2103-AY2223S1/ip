package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseTest() {
        assertEquals(Command.QUIT, Parser.parse("bye"));
    }
}
