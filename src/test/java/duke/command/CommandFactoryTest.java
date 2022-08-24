package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    @Test
    void parseCommand_supportedCommands_success() throws CommandException {
        CommandFactory commandFactory = new CommandFactory();
        // `bye` command
        assertEquals(Command.BYE, commandFactory.parseCommand("bye"));
        assertEquals(Command.BYE, commandFactory.parseCommand("bye bye"));
        // `list` command
        assertEquals(Command.LIST, commandFactory.parseCommand("list"));
        assertEquals(Command.LIST, commandFactory.parseCommand("list all"));
        // `todo` command
        assertEquals(Command.TODO, commandFactory.parseCommand("todo task-1"));
        assertEquals(Command.TODO, commandFactory.parseCommand("todo"));
        // `deadline` command
        assertEquals(Command.DEADLINE,
            commandFactory.parseCommand("deadline task-1 /by 2022-08-23"));
        assertEquals(Command.DEADLINE, commandFactory.parseCommand("deadline /by 2022-08-23"));
        assertEquals(Command.DEADLINE, commandFactory.parseCommand("deadline task-1"));
        // `event` command
        assertEquals(Command.EVENT, commandFactory.parseCommand("event task-1 /at 2022-08-23"));
        assertEquals(Command.EVENT, commandFactory.parseCommand("event /at 2022-08-23"));
        assertEquals(Command.EVENT, commandFactory.parseCommand("event task-1"));
        // `mark` command
        assertEquals(Command.MARK, commandFactory.parseCommand("mark 1"));
        assertEquals(Command.MARK, commandFactory.parseCommand("mark"));
        assertEquals(Command.MARK, commandFactory.parseCommand("mark abc"));
        // `unmark` command
        assertEquals(Command.UNMARK, commandFactory.parseCommand("unmark 2"));
        assertEquals(Command.UNMARK, commandFactory.parseCommand("unmark"));
        assertEquals(Command.UNMARK, commandFactory.parseCommand("unmark abc"));
        // `delete` command
        assertEquals(Command.DELETE, commandFactory.parseCommand("delete 1"));
        assertEquals(Command.DELETE, commandFactory.parseCommand("delete"));
        assertEquals(Command.DELETE, commandFactory.parseCommand("delete abc"));
    }

    @Test
    void parseCommand_unknownCommands_exception() {
        CommandFactory commandFactory = new CommandFactory();
        try {
            commandFactory.parseCommand("hello");
            fail();
        } catch (Exception exception) {
            assertTrue(exception instanceof CommandException);
            assertEquals("Unknown command!", exception.getMessage());
        }
    }
}