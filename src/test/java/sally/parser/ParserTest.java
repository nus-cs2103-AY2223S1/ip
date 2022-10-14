package sally.parser;

import sally.command.AddDeadlineCommand;
import sally.command.AddEventCommand;
import sally.command.AddTodoCommand;
import sally.command.ByeCommand;
import sally.command.DeleteCommand;
import sally.command.ListCommand;
import sally.command.MarkCommand;
import sally.command.UnmarkCommand;
import sally.exception.SallyException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    void parserTrueTest() throws SallyException {
        assertTrue(Parser.parseCommand("bye") instanceof ByeCommand);
        assertTrue(Parser.parseCommand("list") instanceof ListCommand);
        assertTrue(Parser.parseCommand("mark 2") instanceof MarkCommand);
        assertTrue(Parser.parseCommand("unmark 2") instanceof UnmarkCommand);
        assertTrue(Parser.parseCommand("delete 2") instanceof DeleteCommand);
        assertTrue(Parser.parseCommand("todo testing todo") instanceof AddTodoCommand);
        assertTrue(Parser.parseCommand("deadline testing deadline /by string") instanceof AddDeadlineCommand);
        assertTrue(Parser.parseCommand("deadline testing deadline /by 09-12-2022") instanceof AddDeadlineCommand);
        assertTrue(Parser.parseCommand("event testing event /at place") instanceof AddEventCommand);
    }

    @Test
    public void invalidMarkCommandTest() {
        SallyException exception = assertThrows(SallyException.class, () -> {
            Parser.parseMark("mark");
        });
        assertEquals("Oops! Make sure to enter 'mark <index>'", exception.getMessage());
    }

    @Test
    public void invalidUnmarkCommandTest() {
        SallyException exception = assertThrows(SallyException.class, () -> {
            Parser.parseUnmark("unmark");
        });
        assertEquals("Oops! Make sure to enter 'unmark <index>'", exception.getMessage());
    }

    @Test
    public void invalidDeleteCommandTest() {
        SallyException exception = assertThrows(SallyException.class, () -> {
            Parser.parseDelete("delete");
        });
        assertEquals("Oops! I don't understand that, make sure to enter 'delete <index>' :)",
                exception.getMessage());
    }

    @Test
    public void invalidTodoCommandTest() {
        SallyException exception = assertThrows(SallyException.class, () -> {
            Parser.parseToDo("todo");
        });
        assertEquals("Oops! I don't understand that, make sure to enter 'todo <description>' :)",
                exception.getMessage());
    }

    @Test
    public void invalidDeadlineCommandTest() {
        SallyException e1 = assertThrows(SallyException.class, () -> {
            Parser.parseDeadline("deadline");
        });

        SallyException e2 = assertThrows(SallyException.class, () -> {
            Parser.parseDeadline("deadline /by 09-12-2022");
        });

        SallyException e3 = assertThrows(SallyException.class, () -> {
            Parser.parseDeadline("deadline description");
        });

        SallyException e4 = assertThrows(SallyException.class, () -> {
            Parser.parseDeadline("deadline description /by");
        });

        SallyException e5 = assertThrows(SallyException.class, () -> {
            Parser.parseDeadline("deadline /by");
        });

        String expectedException = "Oops! I don't understand that, make sure to enter "
                + "'deadline <description> /by <d-MM-yyyy>' or 'deadline <description> /by <time>' :)";

        assertEquals(expectedException, e1.getMessage());
        assertEquals(expectedException, e2.getMessage());
        assertEquals(expectedException, e3.getMessage());
        assertEquals(expectedException, e4.getMessage());
        assertEquals(expectedException, e5.getMessage());
    }

    @Test
    public void invalidEventCommandTest() {
        SallyException e1 = assertThrows(SallyException.class, () -> {
            Parser.parseEvent("event");
        });

        SallyException e2 = assertThrows(SallyException.class, () -> {
            Parser.parseEvent("event description");
        });

        SallyException e3 = assertThrows(SallyException.class, () -> {
            Parser.parseEvent("event /at");
        });

        SallyException e4 = assertThrows(SallyException.class, () -> {
            Parser.parseEvent("event /at venue");
        });

        SallyException e5 = assertThrows(SallyException.class, () -> {
            Parser.parseEvent("event description /at");
        });

        String expectedException = "Oops! " +
                "I don't understand that, make sure to enter 'event <description> /at <venue>' :)";

        assertEquals(expectedException, e1.getMessage());
        assertEquals(expectedException, e2.getMessage());
        assertEquals(expectedException, e3.getMessage());
        assertEquals(expectedException, e4.getMessage());
        assertEquals(expectedException, e5.getMessage());
    }
}
