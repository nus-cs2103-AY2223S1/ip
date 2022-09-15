package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.commands.ByeCommand;
import duke.exceptions.DukeException;

class ParserTest {


    @Test
    void exit() throws DukeException {
        Parser p = new Parser();
        assertTrue(p.handleGuiInput("Bye") instanceof ByeCommand);
    }

    @Test
    void invalidCommand() {
        Parser p = new Parser();
        DukeException e = assertThrows(DukeException.class, ()
                -> p.handleGuiInput("Add nothing"));
        assertEquals("Invalid command", e.getMessage().trim());
    }

}
