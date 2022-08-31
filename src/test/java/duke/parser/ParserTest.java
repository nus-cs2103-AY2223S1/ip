package duke.parser;

import duke.command.*;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static duke.parser.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void InvalidCommandTest(){
        try {
            assertFalse(parse("happy meal").isExit());
            fail();
        } catch (Exception exception) {
            assertEquals("Please specify a valid command.",
                    exception.getMessage());
        }
    }

    @Test
    public void listTest(){
        try {
            assertFalse(parse("list").isExit());
            assertTrue(parse("list") instanceof ListCommand);
            assertTrue(parse("list ") instanceof ListCommand);
            assertTrue(parse("list tasks") instanceof ListCommand);
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void byeTest(){
        try {
            assertTrue(parse("bye").isExit());
            assertTrue(parse("bye    ").isExit());
            assertTrue(parse("bye bye    ").isExit());
            assertFalse(parse("list").isExit());
            assertFalse(parse("mark 13").isExit());
            assertFalse(parse("unmark 13").isExit());
            assertFalse(parse("delete 13").isExit());
            assertFalse(parse("todo homework").isExit());
            assertFalse(parse("event concert /at 2022-01-01").isExit());
            assertFalse(parse("deadline submission /by 2022-01-02").isExit());
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    public void markTest(){
        try {
            assertFalse(parse("mark 13").isExit());
            assertTrue(parse("mark 13") instanceof MarkCommand);
            assertTrue(parse("mark 13 ") instanceof MarkCommand);
        } catch (Exception exception) {
            fail();
        }
        try {
            parse("mark 13 tasks");
            fail();
        } catch (Exception exception) {
            assertEquals("Please specify a valid number.",
                    exception.getMessage());
        }
        try {
            parse("mark");
            fail();
        } catch (Exception exception) {
            assertEquals("Please specify a task to mark.",
                    exception.getMessage());
        }
    }

    @Test
    public void unmarkTest(){
        try {
            assertFalse(parse("unmark 13").isExit());
            assertTrue(parse("unmark 13") instanceof UnmarkCommand);
            assertTrue(parse("unmark 13 ") instanceof UnmarkCommand);
        } catch (Exception exception) {
            fail();
        }
        try {
            parse("unmark 13 tasks");
            fail();
        } catch (Exception exception) {
            assertEquals("Please specify a valid number.",
                    exception.getMessage());
        }
        try {
            parse("unmark");
            fail();
        } catch (Exception exception) {
            assertEquals("Please specify a task to unmark.",
                    exception.getMessage());
        }
    }

    @Test
    public void deleteTest(){
        try {
            assertFalse(parse("delete 13").isExit());
            assertTrue(parse("delete 13") instanceof DeleteCommand);
            assertTrue(parse("delete 13 ") instanceof DeleteCommand);
        } catch (Exception exception) {
            fail();
        }
        try {
            parse("delete 13 tasks");
            fail();
        } catch (Exception exception) {
            assertEquals("Please specify a valid number.",
                    exception.getMessage());
        }
        try {
            parse("delete");
            fail();
        } catch (Exception exception) {
            assertEquals("Please specify a task to delete.",
                    exception.getMessage());
        }
    }

    @Test
    public void toDoTest(){
        try {
            assertFalse(parse("todo homework").isExit());
            assertTrue(parse("todo homework") instanceof AddCommand);
            assertTrue(parse("todo homework: page 13 ") instanceof AddCommand);
        } catch (Exception exception) {
            fail();
        }
        try {
            parse("todo");
            fail();
        } catch (Exception exception) {
            assertEquals("The description of a todo cannot be empty.",
                    exception.getMessage());
        }
        try {
            parse("todo ");
            fail();
        } catch (Exception exception) {
            assertEquals("The description of a todo cannot be empty.",
                    exception.getMessage());
        }
    }

    @Test
    public void deadlineTest(){
        try {
            assertFalse(parse("deadline homework /by 2022-01-01").isExit());
            assertTrue(parse("deadline homework /by 2022-01-01") instanceof AddCommand);
        } catch (Exception exception) {
            fail();
        }
        try {
            parse("deadline");
            fail();
        } catch (Exception exception) {
            assertEquals("The description of a deadline cannot be empty.",
                    exception.getMessage());
        }
        try {
            parse("deadline homework ");
            fail();
        } catch (Exception exception) {
            assertEquals("Please provide a description and deadline, separated by \"/by\".",
                    exception.getMessage());
        }
        try {
            parse("deadline task /by ");
            fail();
        } catch (Exception exception) {
            assertEquals("Please provide a description and deadline, separated by \"/by\".",
                    exception.getMessage());
        }
        try {
            parse("deadline task /by 01-01-2022");
            fail();
        } catch (Exception exception) {
            assertEquals("Please provide a valid date. (YYYY-MM-DD)",
                    exception.getMessage());
        }
    }

    @Test
    public void eventTest(){
        try {
            assertFalse(parse("event homework /at 2022-01-01").isExit());
            assertTrue(parse("event homework /at 2022-01-01") instanceof AddCommand);
        } catch (Exception exception) {
            fail();
        }
        try {
            parse("event");
            fail();
        } catch (Exception exception) {
            assertEquals("The description of a deadline cannot be empty.",
                    exception.getMessage());
        }
        try {
            parse("event homework ");
            fail();
        } catch (Exception exception) {
            assertEquals("Please provide a description and date, separated by \"/at\".",
                    exception.getMessage());
        }
        try {
            parse("event task /at ");
            fail();
        } catch (Exception exception) {
            assertEquals("Please provide a description and date, separated by \"/at\".",
                    exception.getMessage());
        }
        try {
            parse("event task /at 01-01-2022");
            fail();
        } catch (Exception exception) {
            assertEquals("Please provide a valid date. (YYYY-MM-DD)",
                    exception.getMessage());
        }
    }
}
