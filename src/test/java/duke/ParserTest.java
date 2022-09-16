package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddToDoCommand;

/**
 * Represents a test class for the Parser class.
 */
public class ParserTest {
    @Test
    public void parse_todoBorrowBook_newAddToDoCommand() throws DukeException {
        assertTrue(Parser.parse("todo borrow book") instanceof AddToDoCommand);
        assertEquals(new AddToDoCommand("borrow book"), Parser.parse("todo borrow book"));
    }

    @Test
    public void parse_randomCommand_throwsDukeException() {
        try {
            Parser.parse("random command");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
