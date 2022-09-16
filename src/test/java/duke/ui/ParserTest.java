package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.IncorrectCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;

public class ParserTest {

    @Test
    public void parseCorrectCommand() {
        Command command;
        command = Parser.parse("bye");
        assertEquals(command.getClass(), ExitCommand.class);
        command = Parser.parse("list");
        assertEquals(command.getClass(), ListCommand.class);
        command = Parser.parse("mark 1");
        assertEquals(command.getClass(), MarkCommand.class);
        command = Parser.parse("todo say hi");
        assertEquals(command.getClass(), AddCommand.class);
    }

    @Test
    public void parseIncorrectCommand() {
        Command command = Parser.parse("gibberish");
        assertEquals(command.getClass(), IncorrectCommand.class);
    }
}
