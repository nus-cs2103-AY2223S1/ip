package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class CommandFactoryTest {

    @Test
    void parseCommand_supportedCommands_success() throws CommandException {
        // `bye` command
        assertEquals(Command.BYE, CommandFactory.parseCommand("bye"));
        assertEquals(Command.BYE, CommandFactory.parseCommand("bye bye"));
        // `list` command
        assertEquals(Command.LIST, CommandFactory.parseCommand("list"));
        assertEquals(Command.LIST, CommandFactory.parseCommand("list all"));
        // `todo` command
        assertEquals(Command.TODO, CommandFactory.parseCommand("todo task-1"));
        assertEquals(Command.TODO, CommandFactory.parseCommand("todo"));
        // `deadline` command
        assertEquals(Command.DEADLINE,
            CommandFactory.parseCommand("deadline task-1 /by 2022-08-23"));
        assertEquals(Command.DEADLINE, CommandFactory.parseCommand("deadline /by 2022-08-23"));
        assertEquals(Command.DEADLINE, CommandFactory.parseCommand("deadline task-1"));
        // `event` command
        assertEquals(Command.EVENT, CommandFactory.parseCommand("event task-1 /at 2022-08-23"));
        assertEquals(Command.EVENT, CommandFactory.parseCommand("event /at 2022-08-23"));
        assertEquals(Command.EVENT, CommandFactory.parseCommand("event task-1"));
        // `mark` command
        assertEquals(Command.MARK, CommandFactory.parseCommand("mark 1"));
        assertEquals(Command.MARK, CommandFactory.parseCommand("mark"));
        assertEquals(Command.MARK, CommandFactory.parseCommand("mark abc"));
        // `unmark` command
        assertEquals(Command.UNMARK, CommandFactory.parseCommand("unmark 2"));
        assertEquals(Command.UNMARK, CommandFactory.parseCommand("unmark"));
        assertEquals(Command.UNMARK, CommandFactory.parseCommand("unmark abc"));
        // `delete` command
        assertEquals(Command.DELETE, CommandFactory.parseCommand("delete 1"));
        assertEquals(Command.DELETE, CommandFactory.parseCommand("delete"));
        assertEquals(Command.DELETE, CommandFactory.parseCommand("delete abc"));
        // `tag` command
        assertEquals(Command.TAG, CommandFactory.parseCommand("tag #cs2103 1"));
        assertEquals(Command.TAG, CommandFactory.parseCommand("tag"));
        assertEquals(Command.TAG, CommandFactory.parseCommand("tag abc"));
    }

    @Test
    void parseCommand_unknownCommands_exception() {
        try {
            CommandFactory.parseCommand("hello");
            fail();
        } catch (Exception exception) {
            assertTrue(exception instanceof CommandException);
            assertEquals("Unknown command!", exception.getMessage());
        }
    }
}
