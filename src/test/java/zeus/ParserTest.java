package zeus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import zeus.command.ByeCommand;
import zeus.command.ListCommand;

public class ParserTest {
    @Test
    public void testBye() {
        try {
            assertEquals(true, Parser.parse("bye").isDone());
            assertEquals(true, Parser.parse("bye bye").isDone());
            assertTrue(Parser.parse("bye") instanceof ByeCommand);
        } catch (ZeusException e) {
            fail();
        }
    }

    public void testList() {
        try {
            assertEquals(false, Parser.parse("list").isDone());
            assertEquals(false, Parser.parse("list all").isDone());
            assertTrue(Parser.parse("list") instanceof ListCommand);
        } catch (ZeusException e) {
            fail();
        }
    }

    public void testMark() {
        try {
            assertEquals(false, Parser.parse("mark").isDone());
            fail();
        } catch (ZeusException e) {
            assertEquals("Did you forget to specify which task to delete?", e);
        }
    }

    public void testUnmark() {
        try {
            assertEquals(false, Parser.parse("unmark").isDone());
            fail();
        } catch (ZeusException e) {
            assertEquals("Did you forget to specify which task to delete?", e);
        }
    }
}
