package john.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import john.commands.ByeCommand;
import john.commands.DeadlineCommand;
import john.commands.DeleteCommand;
import john.commands.EventCommand;
import john.commands.FindCommand;
import john.commands.HelpCommand;
import john.commands.IncorrectCommand;
import john.commands.ListCommand;
import john.commands.MarkCommand;
import john.commands.TodoCommand;
import john.commands.UnmarkCommand;

public class ParserTest {
    @Test
    public void parseCommand_todo_todoCommandReturned() {
        assertTrue(new Parser().parseCommand("todo hello") instanceof TodoCommand);
    }

    @Test
    public void parseCommand_deadline_deadlineCommandReturned() {
        assertTrue(new Parser().parseCommand("deadline hello /by 12/12/2022") instanceof DeadlineCommand);
    }

    @Test
    public void parseCommand_event_eventCommandReturned() {
        assertTrue(new Parser().parseCommand("event hello /at 12/12/2022") instanceof EventCommand);
    }

    @Test
    public void parseCommand_list_listCommandReturned() {
        assertTrue(new Parser().parseCommand("list") instanceof ListCommand);
    }

    @Test
    public void parseCommand_mark_markCommandReturned() {
        assertTrue(new Parser().parseCommand("mark 1") instanceof MarkCommand);
    }

    @Test
    public void parseCommand_unmark_unmarkCommandReturned() {
        assertTrue(new Parser().parseCommand("unmark 1") instanceof UnmarkCommand);
    }

    @Test
    public void parseCommand_delete_deleteCommandReturned() {
        assertTrue(new Parser().parseCommand("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void parseCommand_find_findCommandReturned() {
        assertTrue(new Parser().parseCommand("find hello") instanceof FindCommand);
    }

    @Test
    public void parseCommand_help_helpCommandReturned() {
        assertTrue(new Parser().parseCommand("help") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_bye_byeCommandReturned() {
        assertTrue(new Parser().parseCommand("bye") instanceof ByeCommand);
    }

    @Test
    public void parseCommand_incorrect_incorrectCommandReturned() {
        assertTrue(new Parser().parseCommand("hello") instanceof IncorrectCommand);
    }
}
