package duke.parser;

import static org.junit.jupiter.api.Assertions.*;

import duke.command.*;
import duke.common.DukeException;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    void testBye() {
        try {
            Command command = Parser.parse("bye");
            assertEquals(true, command.isExit());
            assertTrue(command instanceof ExitCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testList() {
        try {
            Command command = Parser.parse("list");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof ListCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testFind() {
        try {
            Command command = Parser.parse("find hello");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof FindCommand);
        } catch (Exception e) {
            fail();
        }

        try {
            Parser.parse("find");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid input format!", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testMark() {
        try {
            Command command = Parser.parse("mark 0");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof MarkCommand);
        } catch (Exception e) {
            fail();
        }

        try {
            Parser.parse("mark");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid input format!", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testUnmark() {
        try {
            Command command = Parser.parse("unmark 0");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof UnmarkCommand);
        } catch (Exception e) {
            fail();
        }

        try {
            Parser.parse("unmark");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid input format!", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testDelete() {
        try {
            Command command = Parser.parse("delete 0");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof DeleteCommand);
        } catch (Exception e) {
            fail();
        }

        try {
            Parser.parse("delete");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid input format!", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testTodo() {
        try {
            Command command = Parser.parse("todo hello");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof AddCommand);
        } catch (Exception e) {
            fail();
        }

        try {
            Parser.parse("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid input format!", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testDeadline() {
        try {
            Command command = Parser.parse("deadline hello /by 2022-01-01");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof AddCommand);
        } catch (Exception e) {
            fail();
        }

        try {
            Parser.parse("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid input format!", e.getMessage());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.parse("deadline hello /by 123");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid date format!", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testEvent() {
        try {
            Command command = Parser.parse("event hello /at 2022-01-01");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof AddCommand);
        } catch (Exception e) {
            fail();
        }

        try {
            Parser.parse("event");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid input format!", e.getMessage());
        } catch (Exception e) {
            fail();
        }
        try {
            Parser.parse("event hello /at 123");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid date format!", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }
}
