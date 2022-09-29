package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * ParserTest contains tests for functions in the Parser class.
 */
class ParserTest {
    /**
     * Tests the containsExactKeyword function.
     */
    @Test
    public void containsExactKeywordTest() {
        assertEquals(true, Parser.containsExactKeyword("bye"));
    }

    /**
     * Tests the containsTaskKeyword function.
     */
    @Test
    public void containsTaskKeywordTest() {
        assertEquals(true, Parser.containsTaskKeyword("todo "));
    }
}
