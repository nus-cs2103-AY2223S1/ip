package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.UndoCommand;
import duke.exception.DukeException;

public class UndoCommandTest {

    @Test
    public void undoTodoCommandTest() throws DukeException {
        UndoCommand undoTodoCommand = new UndoCommand("todo", null, -1);
        Parser.parse("todo Coding Assignment");
        assertEquals(Parser.parse("undo"), undoTodoCommand);
    }

    @Test
    public void undoDeadlineCommandTest() throws DukeException {
        UndoCommand undoDeadlineCommand = new UndoCommand("deadline", null, -1);
        Parser.parse("deadline Coding Assignment /by 2022-05-05");
        assertEquals(Parser.parse("undo"), undoDeadlineCommand);
    }

    @Test
    public void undoEventCommandTest() throws DukeException {
        UndoCommand undoEventCommand = new UndoCommand("event", null, -1);
        Parser.parse("event Coding Assignment /at 2022-10-10");
        assertEquals(Parser.parse("undo"), undoEventCommand);
    }

    @Test
    public void undoMarkCommandTest() throws DukeException {
        UndoCommand undoMarkCommand = new UndoCommand("mark", null, 1);
        Parser.parse("todo Coding Assignment");
        Parser.parse("mark 1");
        assertEquals(Parser.parse("undo"), undoMarkCommand);
    }

    @Test
    public void undoUnmarkCommandTest() throws DukeException {
        UndoCommand undoUnmarkCommand = new UndoCommand("unmark", null, 1);
        Parser.parse("todo Coding Assignment");
        Parser.parse("mark 1");
        Parser.parse("unmark 1");
        assertEquals(Parser.parse("undo"), undoUnmarkCommand);
    }

    @Test
    public void undoOtherCommandTest() throws DukeException {
        UndoCommand undoOtherCommand = new UndoCommand("wrong", null, -1);
        Parser.parse("list"); // Cannot undo a list command
        assertEquals(Parser.parse("undo"), undoOtherCommand);

        Parser.parse("help"); // Cannot undo a help command
        assertEquals(Parser.parse("undo"), undoOtherCommand);

        Parser.parse("find Coding"); // Cannot undo a find command
        assertEquals(Parser.parse("undo"), undoOtherCommand);

        Parser.parse("undo"); // Cannot undo an undo command
        assertEquals(Parser.parse("undo"), undoOtherCommand);

        Parser.parse("bye"); // Cannot undo an exit command
        assertEquals(Parser.parse("undo"), undoOtherCommand);
    }

    @Test
    public void undoDeleteCommandTest() throws DukeException {
        UndoCommand undoDeleteCommand = new UndoCommand("delete", null, 1);
        Parser.parse("todo Coding Assignment");
        Parser.parse("delete 1");
        assertEquals(Parser.parse("undo"), undoDeleteCommand);
    }
}
