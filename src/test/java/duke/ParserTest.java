package duke;

import duke.command.AddCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    
    @Test
    public void parse_todoBorrowBook_newAddCommand() throws DukeException {
        assertTrue(Parser.parse("todo borrow book") instanceof AddCommand);
        assertEquals(new AddCommand("todo", "borrow book"), Parser.parse("todo borrow book"));
    }
    
    @Test
    public void parse_randomCommand_throwsDukeException() throws DukeException {
        try {
            Parser.parse("random command");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
