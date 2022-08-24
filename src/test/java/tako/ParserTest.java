package tako;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import tako.command.AddCommand;
import tako.command.DeleteCommand;
import tako.command.ExitCommand;
import tako.command.FindCommand;
import tako.command.ListCommand;
import tako.command.MarkCommand;

public class ParserTest {
    @Test
    public void parse_validInput_success() throws TakoException {
        assertEquals(ExitCommand.class, Parser.parse("bye").getClass());
        assertEquals(ListCommand.class, Parser.parse("list").getClass());
        assertEquals(MarkCommand.class, Parser.parse("mark 1").getClass());
        assertEquals(AddCommand.class, Parser.parse("todo sleep").getClass());
        assertEquals(AddCommand.class,
                Parser.parse("deadline sleep /by 2022-11-11 11:11").getClass());
        assertEquals(AddCommand.class,
                Parser.parse("event sleep /at 2022-11-11 11:11").getClass());
        assertEquals(DeleteCommand.class, Parser.parse("delete 1").getClass());
        assertEquals(FindCommand.class, Parser.parse("find this").getClass());
    }

    @Test
    public void parse_invalidBye_exceptionThrown() {
        try {
            Parser.parse("bye bye");
            fail();
        } catch (TakoException e) {
            assertEquals("The input is invalid.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidList_exceptionThrown() {
        try {
            Parser.parse("list list");
            fail();
        } catch (TakoException e) {
            assertEquals("The input is invalid.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidInput_exceptionThrown() {
        try {
            Parser.parse("testing");
            fail();
        } catch (TakoException e) {
            assertEquals("The input is invalid.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyMarkNumber_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (TakoException e) {
            assertEquals("The task number to mark cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyDeleteNumber_exceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (TakoException e) {
            assertEquals("The task number to delete cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyTodoDescription_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (TakoException e) {
            assertEquals("The description of this todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyDeadlineDescription_exceptionThrown() {
        try {
            Parser.parse("deadline");
            fail();
        } catch (TakoException e) {
            assertEquals("The description of this deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyEventDescription_exceptionThrown() {
        try {
            Parser.parse("event");
            fail();
        } catch (TakoException e) {
            assertEquals("The description of this event cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyDeadlineDateTime_exceptionThrown() {
        try {
            Parser.parse("deadline sleep /by ");
            fail();
        } catch (TakoException e) {
            assertEquals("The description of this deadline's date and time cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyEventDateTime_exceptionThrown() {
        try {
            Parser.parse("event sleep /at ");
            fail();
        } catch (TakoException e) {
            assertEquals("The description of this event's date and time cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDeadlineDateTime_exceptionThrown() {
        try {
            Parser.parse("deadline sleep /by monday 2pm");
            fail();
        } catch (TakoException e) {
            assertEquals("Invalid date and time.\n"
                    + "Date and time should be of the format: yyyy-mm-dd hh:mm\n"
                    + "For example: 2019-10-15 10:30", e.getMessage());
        }
    }

    @Test
    public void parse_invalidEventDateTime_exceptionThrown() {
        try {
            Parser.parse("event sleep /at monday 2pm");
            fail();
        } catch (TakoException e) {
            assertEquals("Invalid date and time.\n"
                    + "Date and time should be of the format: yyyy-mm-dd hh:mm\n"
                    + "For example: 2019-10-15 10:30", e.getMessage());
        }
    }

    @Test
    public void parse_invalidMarkNumber_exceptionThrown() {
        try {
            Parser.parse("mark this");
            fail();
        } catch (TakoException e) {
            assertEquals("The task number to mark is invalid.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDeleteNumber_exceptionThrown() {
        try {
            Parser.parse("delete this");
            fail();
        } catch (TakoException e) {
            assertEquals("The task number to delete is invalid.", e.getMessage());
        }
    }
}
