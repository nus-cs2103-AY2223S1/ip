package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTest() {
        Parser p = new Parser();
        assertEquals(p.parse("todo iP"), true);
        assertEquals(p.parse("event meeting /at 12-09-2022"), true);
        assertEquals(p.parse("deadline homework /by 27-08-2022"), true);
        assertEquals(p.parse("errand group meeting /at 12-07-2023"), false);
    }
}
