package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void containsExactKeywordTest() {
        assertEquals(true, Parser.containsExactKeyword("bye"));
    }

    @Test
    public void containsTaskKeywordTest() {
        assertEquals(true, Parser.containsTaskKeyword("todo "));
    }
}
