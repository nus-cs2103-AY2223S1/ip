package raiden;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import raiden.command.AddCommand;
import raiden.command.DeleteCommand;
import raiden.command.ExitCommand;
import raiden.command.FindCommand;
import raiden.command.ListCommand;
import raiden.command.MarkCommand;
import raiden.command.UnmarkCommand;

public class CommandTest {
    @Test
    public void checks_commandWord_success() throws Exception {
        assertEquals(true, ExitCommand.isCommand("bye"));
        assertEquals(true, ListCommand.isCommand("list"));
        assertEquals(true, MarkCommand.isCommand("mark"));
        assertEquals(true, UnmarkCommand.isCommand("unmark"));
        assertEquals(true, AddCommand.isCommand("todo"));
        assertEquals(true, AddCommand.isCommand("deadline"));
        assertEquals(true, AddCommand.isCommand("event"));
        assertEquals(true, DeleteCommand.isCommand("delete"));
        assertEquals(true, FindCommand.isCommand("find"));
    }
}
