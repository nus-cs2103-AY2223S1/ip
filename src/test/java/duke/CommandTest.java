package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

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
