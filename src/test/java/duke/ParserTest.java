package duke;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests to test the Parser class.
 */
public class ParserTest {

    /** Parser object used in the tests */
    Parser parser = new Parser();

    /**
     * Tests if the bye command works as intended, which should return a ByeCommand object.
     *
     * @throws DukeException Exception thrown when unknown commands are detected.
     */
    @Test
    public void parse_byeCommand_byeCommandInstanceReturned() throws DukeException {
        assertTrue(parser.parse("bye") instanceof ByeCommand);
    }

    /**
     * Tests if the list command works as intended, which should return a ListCommand object.
     *
     * @throws DukeException Exception thrown when unknown commands are detected.
     */
    @Test
    public void parse_listCommand_listCommandInstanceReturned() throws DukeException {
        assertTrue(parser.parse("list") instanceof ListCommand);
    }
}
