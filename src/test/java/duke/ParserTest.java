package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ICommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SaveCommand;
import duke.command.UnmarkCommand;
import duke.command.WrongCommand;

public class ParserTest {
    @Test
    public void parseMethod_addToDo_returnAddCmd() {
        ICommand cmd = Parser.parse("todo homework");
        assertEquals(cmd, new AddCommand(CommandType.TODO, "homework"));
    }

    @Test
    public void parseMethod_addEmptyToDo_returnEmptyCmd() {
        ICommand cmd = Parser.parse("todo ");
        assertEquals(cmd, new WrongCommand("Something went wrong! Could not read TODO."));
    }

    @Test
    public void parseMethod_listInput_returnListCmd() {
        ICommand cmd = Parser.parse("list");
        assertEquals(cmd, new ListCommand());
    }

    @Test
    public void parseMethod_markInput_returnMarkCmd() {
        ICommand cmd = Parser.parse("mark 1");
        assertEquals(cmd, new MarkCommand(new Integer[] { 1 }));
    }

    @Test
    public void parseMethod_unMarkInput_returnUnmarkCmd() {
        ICommand cmd = Parser.parse("unmark 1");
        assertEquals(cmd, new UnmarkCommand(0));
    }

    @Test
    public void parseMethod_deleteInput_returnDeleteCmd() {
        ICommand cmd = Parser.parse("delete 1");
        assertEquals(cmd, new DeleteCommand(0));
    }

    @Test
    public void parseMethod_saveInput_returnSaveCmd() {
        ICommand cmd = Parser.parse("save");
        assertEquals(cmd, new SaveCommand());
    }

    @Test
    public void parseMethod_byeInput_returnExitCmd() {
        ICommand cmd = Parser.parse("bye");
        assertEquals(cmd, new ExitCommand());
    }

    @Test
    public void parseMethod_invalidInput_returnEmptyCmd() {
        ICommand cmd = Parser.parse("whattodo");
        assertEquals(cmd, new WrongCommand("I'm sorry, but I don't understand that."));
    }
}
