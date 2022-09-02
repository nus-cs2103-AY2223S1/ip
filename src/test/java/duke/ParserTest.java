package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;



public class ParserTest {
    private static TaskList tasks;
    private Command c;

    @BeforeAll
    static void setUp() {
        tasks = new TaskList();
    }

    @Test
    public void exitCommandTest() throws DukeException {
        c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    public void listCommandTest() throws DukeException {
        c = Parser.parse("list");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void todoCommandTest() throws DukeException {
        c = Parser.parse("todo abc");
        assertTrue(c instanceof TodoCommand);
    }

    @Test
    public void todoCommandTest2() {
        try {
            c = Parser.parse("todo");
        } catch (DukeException e) {
            assertEquals("\t ☹ OOPS!!!\n\t The description of todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void invalidCommandTest() throws DukeException {
        c = Parser.parse("");
        assertTrue(c instanceof InvalidCommand);
    }

    @Test
    public void markCommandTest() throws DukeException {
        c = Parser.parse("mark 2");
        assertTrue(c instanceof MarkCommand);
    }
}
