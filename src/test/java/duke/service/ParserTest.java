package duke.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.handlers.IHandler;
import duke.handlers.TodoHandler;


public class ParserTest {
    @Test
    public void parseTest() {
        IHandler handler = Parser.parse("todo return book");
        assertEquals(handler instanceof TodoHandler, true);
    }
}
