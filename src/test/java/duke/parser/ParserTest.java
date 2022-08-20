package duke.parser;

import duke.command.*;

import duke.common.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {
    @Test
    public void testBye() {
        try {
            assertEquals(true, Parser.parse("bye").isExit());
            assertEquals(true, Parser.parse("bye 2").isExit());
            assertEquals(true, Parser.parse("bye bye").isExit());
            assertTrue(Parser.parse("bye") instanceof ExitCommand);
            assertTrue(Parser.parse("bye 2") instanceof ExitCommand);
            assertTrue(Parser.parse("bye bye") instanceof ExitCommand);
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void testList() {
        try {
            assertEquals(false, Parser.parse("list").isExit());
            assertEquals(false, Parser.parse("list 2").isExit());
            assertEquals(false, Parser.parse("list list").isExit());
            assertTrue(Parser.parse("list") instanceof ListCommand);
            assertTrue(Parser.parse("list 2") instanceof ListCommand);
            assertTrue(Parser.parse("list list") instanceof ListCommand);
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void testFind() {
        try {
            assertEquals(false, Parser.parse("find").isExit());
            assertEquals(false, Parser.parse("find read").isExit());
            assertTrue(Parser.parse("find") instanceof FindCommand);
            assertTrue(Parser.parse("find read") instanceof FindCommand);
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void testMark() {
        try {
            assertEquals(false, Parser.parse("mark").isExit());
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! No task index is specified :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            assertEquals(false, Parser.parse("mark NaN").isExit());
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! You didn't give a valid index :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            assertEquals(false, Parser.parse("mark 10").isExit());
            assertTrue(Parser.parse("mark 10") instanceof MarkCommand);
        } catch (Exception exception) {
            fail();
        }
        try {
            assertEquals(false, Parser.parse("mark -1 Ten").isExit());
            assertTrue(Parser.parse("mark -1 Ten") instanceof MarkCommand);
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void testUnMark() {
        try {
            assertEquals(false, Parser.parse("unmark").isExit());
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! No task index is specified :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            assertEquals(false, Parser.parse("unmark NaN").isExit());
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! You didn't give a valid index :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            assertEquals(false, Parser.parse("unmark 10").isExit());
            assertTrue(Parser.parse("unmark 10") instanceof UnMarkCommand);
        } catch (Exception exception) {
            fail();
        }
        try {
            assertEquals(false, Parser.parse("unmark -1 Ten").isExit());
            assertTrue(Parser.parse("unmark -1 Ten") instanceof UnMarkCommand);
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void testDelete() {
        try {
            assertEquals(false, Parser.parse("delete").isExit());
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! No task index is specified :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            assertEquals(false, Parser.parse("delete NaN").isExit());
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! You didn't give a valid index :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            assertEquals(false, Parser.parse("delete 10").isExit());
            assertTrue(Parser.parse("delete 10") instanceof DeleteCommand);
        } catch (Exception exception) {
            fail();
        }
        try {
            assertEquals(false, Parser.parse("delete -1 Ten").isExit());
            assertTrue(Parser.parse("delete -1 Ten") instanceof DeleteCommand);
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void testToDo() {
        try {
            Command command = Parser.parse("todo");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("todo \n  \t \n");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("todo one two three");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof AddCommand);
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void testEvent() {
        try {
            Command command = Parser.parse("event");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! There is no /at argument for event :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event one two four");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! There is no /at argument for event :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event /");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! There is no /at argument for event :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event six /by");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! There is no /at argument for event :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event six /by 2020-12-12");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! There is no /at argument for event :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event event /at time");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event event /at 10000-01-01");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event event /at 1000-00-00");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event event /at 2000-12-32");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event event /at 2003-02-29");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event /at 2004-02-29");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! The description of a event cannot be empty.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event my_event /at 2004-02-29");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof AddCommand);
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("event \n \t my/at/event \t\t    /at \n 2004-02-29");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof AddCommand);
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void testDeadline() {
        try {
            Command command = Parser.parse("deadline");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! There is no /by argument for deadline :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("deadline one two four");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! There is no /by argument for deadline :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("deadline /");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! There is no /by argument for deadline :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("deadline six /at");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! There is no /by argument for deadline :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("deadline six /at 2020-12-12");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! There is no /by argument for deadline :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }

        try {
            Command command = Parser.parse("deadline event /by time");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("deadline event /by 10000-01-01");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("deadline event /by 1000-00-00");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("deadline event /by 2000-12-32");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("deadline event /by 2003-02-29");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("deadline /by 2004-02-29");
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("deadline my_event /by 2004-02-29");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof AddCommand);
        } catch (Exception exception) {
            fail();
        }
        try {
            Command command = Parser.parse("deadline \n \t my/by/event \t\t    /by \n 2004-02-29");
            assertEquals(false, command.isExit());
            assertTrue(command instanceof AddCommand);
        } catch (Exception exception) {
            fail();
        }
    }
}
