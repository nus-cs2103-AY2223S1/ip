package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void commandToTask() {
        assertEquals(new Todo("123"), Parser.commandToTask("todo 123"));
        assertNotEquals(new Todo(" 123"), Parser.commandToTask("todo 123"));
    }

    @Test
    void parse() {
        try {
            assertEquals(
                    new AddCommand("todo 123"),
                    Parser.parse("todo 123")
            );
            assertNotEquals(
                    new AddCommand("todo 123"),
                    Parser.parse("list")
            );
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }
}