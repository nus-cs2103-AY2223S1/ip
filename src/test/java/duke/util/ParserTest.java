package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.command.AddTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.EmptyCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UpdateStatusCommand;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

public class ParserTest {


    @Test
    public void parseInt_float_throwsException() {
        try {
            Parser.parseInt("1.0");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: 1.0", e.getMessage());
        }
    }

    @Test
    public void parseInt_string_throwsException() {
        try {
            Parser.parseInt("One");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: One", e.getMessage());
        }
    }

    @Test
    public void parseInt_integer_returnsInteger() {
        assertEquals(1, Parser.parseInt("1"));
    }

    @Test
    public void parseDateTime_noTime_throwsException() {
        try {
            Parser.parseDateTime("1-1-1");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: 1-1-1", e.getMessage());
        }
    }

    @Test
    public void parseDateTime_noDate_throwsException() {
        try {
            Parser.parseDateTime("0100");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: 0100", e.getMessage());
        }
    }

    @Test
    public void parseDateTime_nonDateTime_throwsException() {
        try {
            Parser.parseDateTime("Hello");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: Hello", e.getMessage());
        }
    }

    @Test
    public void parseDateTime_wrongFormat_throwsException() {
        try {
            Parser.parseDateTime("1-1-1 0100");
            fail();
        } catch (DukeException e) {
            assertEquals("Parsing error: 1-1-1 0100", e.getMessage());
        }

        try {
            Parser.parseDateTime("01-01-1 0100");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: 01-01-1 0100", e.getMessage());
        }


        try {
            Parser.parseDateTime("01-01-2022 0100");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: 01-01-2022 0100", e.getMessage());
        }
    }

    @Test
    public void parseDateTime_dateTime_returnsDateTime() {
        assertEquals(LocalDateTime.of(2019, 1, 1, 12, 12), Parser.parseDateTime("1-1-19 1212"));
        assertEquals(LocalDateTime.of(2019, 1, 1, 12, 12), Parser.parseDateTime("01-01-19 1212"));
        assertEquals(LocalDateTime.of(2010, 12, 12, 9, 0), Parser.parseDateTime("12-12-10 0900"));
        assertEquals(LocalDateTime.of(2099, 12, 12, 9, 0), Parser.parseDateTime("12-12-99 0900"));
    }

    @Test
    public void parseCommand_noSpaceAroundSeparator_throwsException() {
        try {
            Parser.parseCommand("deadline Description/ 1-1-19 1212");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: deadline Description/ 1-1-19 1212"
                    + " (wrong number of arguments provided)", e.getMessage());
        }
    }

    @Test
    public void parseCommand_extraSpace_returnsCommand() {
        assertEquals(new AddTaskCommand(new DeadlineTask("Description", Parser.parseDateTime("1-1-19 1212"))),
                Parser.parseCommand("deadline Description /         1-1-19 1212"));
        assertEquals(new AddTaskCommand(new DeadlineTask("Description", Parser.parseDateTime("1-1-19 1212"))),
                Parser.parseCommand("deadline Description      / 1-1-19 1212"));
        assertEquals(new AddTaskCommand(new DeadlineTask("Description", Parser.parseDateTime("1-1-19 1212"))),
                Parser.parseCommand("deadline         Description      / 1-1-19 1212"));
        assertEquals(new ListCommand(), Parser.parseCommand("list                 "));
        assertEquals(new ExitCommand(), Parser.parseCommand("exit                 "));
        assertEquals(new DeleteTaskCommand(1), Parser.parseCommand("delete     1             "));
        assertEquals(new UpdateStatusCommand(1, true), Parser.parseCommand("check   1"));
        assertEquals(new UpdateStatusCommand(1, false), Parser.parseCommand("uncheck   1"));
        assertEquals(new EmptyCommand(), Parser.parseCommand("                  "));
        assertEquals(new FindCommand("Keyword"), Parser.parseCommand("find       Keyword   "));
    }

    @Test
    public void parseCommand_unknownCommand_throwsException() {
        try {
            Parser.parseCommand("add new task");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: add new task (unknown command)", e.getMessage());
        }
    }

    @Test
    public void parseCommand_extraArgument_throwsException() {
        try {
            Parser.parseCommand("list todo");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: list todo (wrong number of arguments provided)", e.getMessage());
        }

        try {
            Parser.parseCommand("exit now");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: exit now (wrong number of arguments provided)", e.getMessage());
        }
    }

    @Test
    public void parseCommand_correctCommand_returnsCommand() {
        assertEquals(new AddTaskCommand(new TodoTask("Description")), Parser.parseCommand("todo Description"));
        assertEquals(new AddTaskCommand(new EventTask("Description", Parser.parseDateTime("1-1-19 1212"))),
                Parser.parseCommand("event Description / 1-1-19 1212"));
        assertEquals(new AddTaskCommand(new DeadlineTask("Description", Parser.parseDateTime("1-1-19 1212"))),
                Parser.parseCommand("deadline Description / 1-1-19 1212"));
        assertEquals(new ListCommand(), Parser.parseCommand("list"));
        assertEquals(new ExitCommand(), Parser.parseCommand("exit"));
        assertEquals(new DeleteTaskCommand(1), Parser.parseCommand("delete 1"));
        assertEquals(new UpdateStatusCommand(1, true), Parser.parseCommand("check 1"));
        assertEquals(new UpdateStatusCommand(1, false), Parser.parseCommand("uncheck 1"));
        assertEquals(new EmptyCommand(), Parser.parseCommand(""));
        assertEquals(new FindCommand("Keyword"), Parser.parseCommand("find Keyword"));
    }

    @Test
    public void parseTask_emptyString_throwsException() {
        try {
            Parser.parseTask("");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: ", e.getMessage());
        }
    }

    @Test
    public void parseTask_wrongSymbol_throwsException() {
        try {
            Parser.parseTask("# | 0 | Task Description");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: # | 0 | Task Description", e.getMessage());
        }
    }

    @Test
    public void parseTask_noSpaceAroundSeperator_throwsException() {
        try {
            Parser.parseTask("T |0|Task Description");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: T |0|Task Description", e.getMessage());
        }
    }

    @Test
    public void parseTask_extraSpace_throwsException() {
        try {
            Parser.parseTask("T | 0   | Task Description");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: T | 0   | Task Description", e.getMessage());
        }
    }

    @Test
    public void parseTask_lessArgument_throwsException() {
        try {
            Parser.parseTask("T | 0 description");
            fail();
        } catch (ParseException e) {
            assertEquals("Parsing error: T | 0 description", e.getMessage());
        }
    }

    @Test
    public void parseTask_correctTask_returnsTask() {
        assertEquals(new TodoTask("Task Description"), Parser.parseTask("T | 0 | Task Description"));
        assertEquals(new DeadlineTask("Task Description", LocalDateTime.of(2019, 1, 1, 12, 12)),
                Parser.parseTask("D | 0 | Task Description | 1-1-19 1212"));
        assertEquals(new EventTask("Task Description", LocalDateTime.of(2019, 1, 1, 12, 12)),
                Parser.parseTask("E | 0 | Task Description | 1-1-19 1212"));

        TodoTask todoTask = new TodoTask("Task Description");
        todoTask.setDone(true);
        assertEquals(todoTask, Parser.parseTask("T | 1 | Task Description"));

        DeadlineTask deadlineTask = new DeadlineTask("Task Description", LocalDateTime.of(2019, 1, 1, 12, 12));
        deadlineTask.setDone(true);
        assertEquals(deadlineTask, Parser.parseTask("D | 1 | Task Description | 1-1-19 1212"));

        EventTask eventTask = new EventTask("Task Description", LocalDateTime.of(2019, 1, 1, 12, 12));
        eventTask.setDone(true);
        assertEquals(eventTask, Parser.parseTask("E | 1 | Task Description | 1-1-19 1212"));
    }
}
