package spongebob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import spongebob.command.AddCommand;
import spongebob.command.CommandType;
import spongebob.command.DeleteCommand;
import spongebob.command.ExitCommand;
import spongebob.command.ICommand;
import spongebob.command.ListCommand;
import spongebob.command.MarkCommand;
import spongebob.command.SaveCommand;
import spongebob.command.UnmarkCommand;
import spongebob.command.WrongCommand;

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
        assertEquals(cmd, new UnmarkCommand(new Integer[] { 1 }));
    }

    @Test
    public void parseMethod_deleteInput_returnDeleteCmd() {
        ICommand cmd = Parser.parse("delete 1");
        assertEquals(cmd, new DeleteCommand(new Integer[] { 1 }));
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
