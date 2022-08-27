package duke;

import duke.command.Command;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.MarkCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.ByeCommand;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void testParseTodo() {
        String command = "todo Finish CS2103T iP";
        Command c = Parser.parse(command);
        assertEquals(c, new TodoCommand("Finish CS2103T iP"));
    }

    @Test
    public void testParseTodoEmpty() {
        String command = "todo";
        assertThrows(DukeException.class, () -> Parser.parse(command));
    }

    @Test
    public void testParseDeadline() {
        String command = "deadline Finish CS2103T iP /by 2022-01-01";
        Command c = Parser.parse(command);
        assertEquals(c, new DeadlineCommand("Finish CS2103T iP", "by 2022-01-01"));
    }

    @Test
    public void testParseDeadlineEmpty() {
        String command = "deadline";
        assertThrows(DukeException.class, () -> Parser.parse(command));
    }

    @Test
    public void testParseDeadlineNoDate() {
        String command = "deadline Finish CS2103T iP";
        assertThrows(DukeException.class, () -> Parser.parse(command));
    }

    @Test
    public void testParseEvent() {
        String command = "event CS2103T iP lecture /on 2022-08-26";
        Command c = Parser.parse(command);
        assertEquals(c, new EventCommand("CS2103T iP lecture", "on 2022-08-26"));
    }

    @Test
    public void testParseEventEmpty() {
        String command = "event";
        assertThrows(DukeException.class, () -> Parser.parse(command));
    }

    @Test
    public void testParseEventNoDate() {
        String command = "event CS2103T iP lecture";
        assertThrows(DukeException.class, () -> Parser.parse(command));
    }

    @Test
    public void testParseInvalid() {
        String command = "invalid";
        assertThrows(DukeException.class, () -> {
            Parser.parse(command);
        });
    }

    @Test
    public void testParseMark() {
        String command = "mark 1";
        Command c = Parser.parse(command);
        assertEquals(c, new MarkCommand(0, true));
    }

    @Test
    public void testParseUnmark() {
        String command = "unmark 4";
        Command c = Parser.parse(command);
        assertEquals(c, new MarkCommand(3, false));
    }

    @Test
    public void testParseMarkInvalid() {
        String command = "mark asd";
        assertThrows(DukeException.class, () -> {
            Parser.parse(command);
        });
    }

    @Test
    public void testParseDelete() {
        String command = "delete 1";
        Command c = Parser.parse(command);
        assertEquals(c, new DeleteCommand(0));
    }

    @Test
    public void testParseList() {
        String command = "list";
        Command c = Parser.parse(command);
        assertEquals(c, new ListCommand());
    }

    @Test
    public void testParseBye() {
        String command = "bye";
        Command c = Parser.parse(command);
        assertEquals(c, new ByeCommand());
    }
}
