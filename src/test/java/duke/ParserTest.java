package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.command.*;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {
    @Test
    public void convertToDate_validDate_localDateReturned() {
        try {
            LocalDate date = Parser.convertToDateObj("2022-02-02");
            assertEquals(date, LocalDate.parse("2022-02-02"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void convertToDate_invalidString_dukeExceptionThrown() {
        try {
            Parser.convertToDateObj("someRandomString");
            fail();
        } catch (DukeException e) {
            //Pass
        }
    }

    @Test
    public void printDateTest() {
        LocalDate date = LocalDate.parse("2022-02-02");
        String expected = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String actual = Parser.printDate(date);
        assertEquals(expected, actual);
    }

    @Test
    public void printSaveFileDateTest() {
        LocalDate date = LocalDate.parse("2022-02-02");
        String expected = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String actual = Parser.printSaveFileDate(date);
        assertEquals(expected, actual);
    }

    @Test
    public void parseUserInput_byeCommand_returnByeCommandInstance() {
        try {
            assertTrue(Parser.parseUserInput("bye") instanceof ByeCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_todoCommand_returnToDoCommandInstance() {
        try {
            String command = "todo ijbnegini";
            assertTrue(Parser.parseUserInput(command) instanceof TodoCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_deadlineCommand_returnDeadlineCommandInstance() {
        try {
            String command = "deadline wienginf /by 2022-10-10";
            assertTrue(Parser.parseUserInput(command) instanceof DeadlineCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_eventCommand_returnDeadlineCommandInstance() {
        try {
            String command = "event woedgn /at 2022-10-10";
            assertTrue(Parser.parseUserInput(command) instanceof EventCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_markCommand_returnMarkCommandInstance() {
        try {
            String command = "mark 2";
            assertTrue(Parser.parseUserInput(command) instanceof MarkCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_unmarkCommand_returnUnmarkCommandInstance() {
        try {
            String command = "unmark 2";
            assertTrue(Parser.parseUserInput(command) instanceof UnmarkCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_deleteCommand_returnDeleteCommandInstance() {
        try {
            String command = "delete 3";
            assertTrue(Parser.parseUserInput(command) instanceof DeleteCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_priorityCommand_returnPriorityCommandInstance() {
        try {
            String command1 = "priority 1 high";
            String command2 = "priority 2 medium";
            String command3 = "priority 3 low";
            String command4 = "priority 4 none";

            assertTrue(Parser.parseUserInput(command1) instanceof PriorityCommand);
            assertTrue(Parser.parseUserInput(command2) instanceof PriorityCommand);
            assertTrue(Parser.parseUserInput(command3) instanceof PriorityCommand);
            assertTrue(Parser.parseUserInput(command4) instanceof PriorityCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_findCommand_returnFindCommandInstance() {
        try {
            String command = "find wjaeni";
            assertTrue(Parser.parseUserInput(command) instanceof FindCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_listCommand_returnListCommandInstance() {
        try {
            String command = "list";
            assertTrue(Parser.parseUserInput(command) instanceof ListCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_invalidCommands_throwDukeExceptions() {
        String[] invalidCommands = {"wnegfn", "todo", "deadline", "event", "deadline wwoefn",
                "event iwfi", "find", "priority", "priority 1"};

        for(int i = 0; i < invalidCommands.length; ++i) {
            try{
                Parser.parseUserInput(invalidCommands[i]);
                fail();
            } catch (DukeException e) {
                //pass;
            }
        }
    }
}
