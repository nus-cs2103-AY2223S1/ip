package duke.service;

import org.junit.jupiter.api.Test;
import duke.handlers.IHandler;
import duke.handlers.TodoHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTest(){
        IHandler handler = Parser.parse("todo return book");
        assertEquals(handler instanceof TodoHandler, true);
    }
}
