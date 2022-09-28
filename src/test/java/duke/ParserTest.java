package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.DukeCommandType;

public class ParserTest {
    @Test
    public void getCommandExit() {
        String command = "exit";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.EXIT, newCommand.getCommandType());
    }

    @Test
    public void getCommandExit_extraSpaces() {
        String command = "exit  ";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.EXIT, newCommand.getCommandType());
    }

    @Test
    public void getCommandQuit() {
        String command = "quit";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.EXIT, newCommand.getCommandType());
    }

    @Test
    public void getCommandQuit_extraSpaces() {
        String command = "quit  ";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.EXIT, newCommand.getCommandType());
    }

    @Test
    public void getCommandBye() {
        String command = "bye";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.EXIT, newCommand.getCommandType());
    }

    @Test
    public void getCommandBye_extraSpaces() {
        String command = "bye   ";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.EXIT, newCommand.getCommandType());
    }

    @Test
    public void getCommandList() {
        String command = "list";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.LIST, newCommand.getCommandType());
    }

    @Test
    public void getCommandList_extraSpaces() {
        String command = "list   ";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.LIST, newCommand.getCommandType());
    }

    @Test
    public void getCommandMark() {
        String command = "mark 1";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.MARK, newCommand.getCommandType());
        assertEquals("1", newCommand.getArgs());
    }

    @Test
    public void getCommandMark_extraSpaces() {
        String command = "mark   1";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.MARK, newCommand.getCommandType());
        assertEquals("1", newCommand.getArgs());
    }

    @Test
    public void getCommandUnmark() {
        String command = "unmark 1";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.UNMARK, newCommand.getCommandType());
        assertEquals("1", newCommand.getArgs());
    }

    @Test
    public void getCommandUnmark_extraSpaces() {
        String command = "unmark   1";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.UNMARK, newCommand.getCommandType());
        assertEquals("1", newCommand.getArgs());
    }

    @Test
    public void getCommandDelete() {
        String command = "delete 1";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.DELETE, newCommand.getCommandType());
        assertEquals("1", newCommand.getArgs());
    }

    @Test
    public void getCommandDelete_extraSpaces() {
        String command = "delete   1";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.DELETE, newCommand.getCommandType());
        assertEquals("1", newCommand.getArgs());
    }

    @Test
    public void getCommandRemove() {
        String command = "remove 1";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.DELETE, newCommand.getCommandType());
        assertEquals("1", newCommand.getArgs());
    }

    @Test
    public void getCommandRemove_extraSpaces() {
        String command = "remove   1";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.DELETE, newCommand.getCommandType());
        assertEquals("1", newCommand.getArgs());
    }

    @Test
    public void getCommandTodo() {
        String command = "todo something";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.TODO, newCommand.getCommandType());
        assertEquals("something", newCommand.getArgs());
    }

    @Test
    public void getCommandTodo_extraSpaces() {
        String command = "todo    something   at somewhere    at sometime";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.TODO, newCommand.getCommandType());
        assertEquals("something at somewhere at sometime", newCommand.getArgs());
    }

    @Test
    public void getCommandDeadline() {
        String command = "deadline something /by 2022-21-08 1551";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.DEADLINE, newCommand.getCommandType());
        assertEquals("something /by 2022-21-08 1551", newCommand.getArgs());
    }

    @Test
    public void getCommandEvent() {
        String command = "event something /at 2022-21-08 1551";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.EVENT, newCommand.getCommandType());
        assertEquals("something /at 2022-21-08 1551", newCommand.getArgs());
    }

    @Test
    public void getCommandUnknown1() {
        String command = "unknown";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.UNKNOWN, newCommand.getCommandType());
    }

    @Test
    public void getCommandUnknown2() {
        String command = "garbage";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.UNKNOWN, newCommand.getCommandType());
    }

    @Test
    public void getCommandUnknown3() {
        String command = "garbage in garbage out";
        Command newCommand = Parser.getCommand(command);
        assertEquals(DukeCommandType.UNKNOWN, newCommand.getCommandType());
    }
}
