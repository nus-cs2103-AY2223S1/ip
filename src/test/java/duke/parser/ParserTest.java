package duke.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AllCommand;
import duke.command.AnyCommand;
import duke.command.AtCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyCommandException;

public class ParserTest {
    private final Parser sampleParser = new Parser();

    @Test
    public void parseText_inputStartsWithBye_isListeningBecomeFalse() {
        try {
            sampleParser.parseText("bye");
            assertFalse(sampleParser.getIsListening());
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputStartsWithBye_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText("bye");
            assertTrue(command instanceof ByeCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputStartsWithList_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText("list");
            assertTrue(command instanceof ListCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputStartsWithMark_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText("Mark 2");
            assertTrue(command instanceof MarkCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputStartsWithUnmark_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText("Unmark 1");
            assertTrue(command instanceof UnmarkCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputStartsWithDelete_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText("Delete 3");
            assertTrue(command instanceof DeleteCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputStartsWithToDo_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText("todo eat #lunch");
            assertTrue(command instanceof ToDoCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputStartsWithEvent_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText(
                    "event interview /at 2022-09-12 #internship");
            assertTrue(command instanceof EventCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputStartsWithDeadline_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText(
                    "deadline solve leetcode /by 2022-09-10 #grind");
            assertTrue(command instanceof DeadlineCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputStartsWithFind_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText("Find cs2103t, cs2101");
            assertTrue(command instanceof FindCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputStartsWithAt_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText("at 2022-11-02");
            assertTrue(command instanceof AtCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputStartsWithAll_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText("all uni, internship");
            assertTrue(command instanceof AllCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }


    @Test
    public void parseText_inputStartsWithAny_returnCorrectCommand() {
        try {
            Command command = sampleParser.parseText("any uni, internship");
            assertTrue(command instanceof AnyCommand);
        } catch (DukeException error) {
            fail("Should not have thrown any error");
        }
    }

    @Test
    public void parseText_inputIsEmpty_throwError() {
        try {
            sampleParser.parseText("");
            fail("Should have thrown EmptyCommandException");
        } catch (DukeException error) {
            assertTrue(error instanceof EmptyCommandException);
        }
    }

    @Test
    public void parseText_invalidInput_throwError() {
        try {
            sampleParser.parseText("get all tasks");
            fail("Should have thrown DukeException");
        } catch (DukeException error) {
            return;
        }
    }
}
