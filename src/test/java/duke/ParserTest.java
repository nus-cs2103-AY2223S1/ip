package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    private Command c;
    private static TaskList tasks;

    @BeforeAll
    static void setUp() {
        tasks = new TaskList();
    }

    @Test
    public void exitCommandTest() throws DukeException {
        c = Parser.parse(tasks, "bye");
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    public void listCommandTest() throws DukeException {
        c = Parser.parse(tasks, "list");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void todoCommandTest() throws DukeException {
        c = Parser.parse(tasks, "todo");
        assertTrue(c instanceof TodoCommand);
    }

    @Test
    public void todoCommandTest2() {
        try {
            c = Parser.parse(tasks, "todo");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "\t ☹ OOPS!!!\n\t The description of a todo cannot be empty.");
        }
    }

    @Test
    public void invalidCommandTest() throws DukeException {
        c = Parser.parse(tasks, "");
        assertTrue(c instanceof InvalidCommand);
    }

    @Test
    public void markCommandTest() throws DukeException {
        c = Parser.parse(tasks, "mark 2");
        assertTrue(c instanceof MarkCommand);
    }
}
