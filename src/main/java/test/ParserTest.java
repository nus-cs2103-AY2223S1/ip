package test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.Todo;
import duke.command.AddCommand;


/**
 * Test the Parser class.
 */
class ParserTest {

    /**
     * Test the Parser's commandToTask method.
     */
    @Test
    public void commandToTask() throws DukeException {
        Assertions.assertEquals(new Todo("123"), Parser.commandToTask("todo 123"));
        assertNotEquals(new Todo(" 123"), Parser.commandToTask("todo 123"));
    }

    /**
     * Test the Parser's parse method.
     */
    @Test
    public void parse() {
        try {
            Assertions.assertEquals(
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
