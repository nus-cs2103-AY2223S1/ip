package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


/**
 * Test the Parser class.
 */
class ParserTest {

    /**
     * Test the Parser's commandToTask method.
     */
    @Test
    public void commandToTask() throws DukeException {
        Assertions.assertEquals(new Todo("todo"),
                Parser.addCommandToTask("todo todo"));
        Assertions.assertEquals(new Event("event", "2020-01-01"),
                Parser.addCommandToTask("event event /at 2020-01-01"));
        Assertions.assertEquals(new Deadline("deadline", "2020-02-02"),
                Parser.addCommandToTask("deadline deadline /by 2020-02-02"));

    }

    /**
     * Test the Parser's parse method.
     */
    @Test
    public void parse() {
        try {
            Assertions.assertEquals(
                    new AddCommand("todo todo"),
                    Parser.parse("todo todo")
            );
            Assertions.assertEquals(
                    new AddCommand("event event /at 2020-01-01"),
                    Parser.parse("event event /at 2020-01-01")
            );
            Assertions.assertEquals(
                    new AddCommand("deadline deadline /by 2020-02-02"),
                    Parser.parse("deadline deadline /by 2020-02-02")
            );
            Assertions.assertEquals(
                    new DeleteCommand(1),
                    Parser.parse("delete 1")
            );
            Assertions.assertEquals(
                    new AddCommand("deadline deadline /by 2020-02-02"),
                    Parser.parse("deadline deadline /by 2020-02-02")
            );
            Assertions.assertEquals(
                    new FindCommand("x"),
                    Parser.parse("find x")
            );
            Assertions.assertEquals(
                    new ListCommand(),
                    Parser.parse("list")
            );
            Assertions.assertEquals(
                    new MarkCommand(1),
                    Parser.parse("mark 1")
            );
            Assertions.assertEquals(
                    new UnmarkCommand(1),
                    Parser.parse("unmark 1")
            );
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }
}
