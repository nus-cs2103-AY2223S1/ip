package duke.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void parseTest() {
        String[] tokens = Parser.parse("todo return book");
        // Not sure if
        assertArrayEquals(tokens, new String[]{"todo", "return book", null, null});
    }
}
