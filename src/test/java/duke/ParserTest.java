package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;
import duke.command.MarkUndoneCommand;
import duke.command.OnDateCommand;
import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testBye() {
        try {
            assertEquals(true, Parser.parse("bye").isDone());
            assertEquals(true, Parser.parse("bye bye").isDone());
            assertTrue(Parser.parse("bye") instanceof ByeCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    public void testList() {
        try {
            assertEquals(false, Parser.parse("list").isDone());
            assertEquals(false, Parser.parse("list all").isDone());
            assertTrue(Parser.parse("list") instanceof ListCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    public void testMark() {
        try {
            assertEquals(false, Parser.parse("mark").isDone());
            fail();
        } catch (DukeException e) {
            assertEquals("Did you forget to specify which task to delete?", e);
        }
    }

    public void testUnmark() {
        try {
            assertEquals(false, Parser.parse("unmark").isDone());
            fail();
        } catch (DukeException e) {
            assertEquals("Did you forget to specify which task to delete?", e);
        }
    }
}
